package org.example.hotelbooking.service;

import org.example.hotelbooking.common.util.PriceCalculator;
import org.example.hotelbooking.domain.Booking;
import org.example.hotelbooking.domain.Room;
import org.example.hotelbooking.dto.BookingResponse;
import org.example.hotelbooking.dto.CreateBookingRequest;
import org.example.hotelbooking.exception.BadRequestException;
import org.example.hotelbooking.exception.ResourceNotFoundException;
import org.example.hotelbooking.repository.BookingRepository;
import org.example.hotelbooking.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.example.hotelbooking.domain.BookingStatus.CANCELLED;
import static org.example.hotelbooking.domain.RoomStatus.AVAILABLE;
import static org.example.hotelbooking.domain.RoomStatus.INACTIVE;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional
    public BookingResponse createBooking(CreateBookingRequest request) {
        Instant checkIn = request.checkInDateTime();
        Instant checkOut = request.checkOutDateTime();
        if (checkOut.isBefore(checkIn) || checkOut.equals(checkIn)) {
            throw new BadRequestException("Thời gian check-out phải diễn ra sau check-in.");
        }
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (nights == 0) {
            nights = 1;
        }
        Room room = roomRepository.findById(request.roomId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phòng với ID đã cung cấp."));
        if(room.getStatus()==INACTIVE){
            throw new BadRequestException("Phòng đã được đặt.");
        }
        Booking booking = new Booking();
        booking.setCustomerName(request.customerName());
        booking.setCustomerCccd(request.customerCccd());
        booking.setNote(request.note());
        booking.setCheckInDateTime(checkIn);
        booking.setCheckOutDateTime(checkOut);
        if(request.numberOfGuests()>room.getRoomType().getMaxOccupancy()){
            throw new BadRequestException("Số lượng khách quá giới hạn phòng!!");
        }
        booking.setNumberOfGuests(request.numberOfGuests());
        booking.setRoom(room);
        BigDecimal roomPrice = room.getRoomType().getBasePrice();
        booking.setTotalPrice(PriceCalculator.calculate(roomPrice, nights));
        bookingRepository.save(booking);
        room.setStatus(INACTIVE);
        roomRepository.save(room);
        BookingResponse bookingResponse = BookingResponse.from(booking);
        return bookingResponse;
    }
    @Transactional(readOnly = true)
    public BookingResponse getBookingById(String id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking",id));
        return BookingResponse.from(booking);
    }
    @Transactional(readOnly = true)
    public List<BookingResponse> getBookingsByCCCD(String cccd){
        return bookingRepository.findBookingByCccd(cccd).stream()
                .map(BookingResponse::from)
                .toList();
    }
    @Transactional
    public BookingResponse cancelBooking(String id){
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking",id));
        if(booking.getStatus()==CANCELLED){
            throw new IllegalArgumentException("Booking này đã bị hủy!!!");
        }
        booking.setStatus(CANCELLED);
        Room room = booking.getRoom();
        room.setStatus(AVAILABLE);
        roomRepository.save(room);
        bookingRepository.save(booking);
        return BookingResponse.from(booking);
    }
    // TO DO
}
