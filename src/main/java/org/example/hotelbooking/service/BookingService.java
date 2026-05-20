package org.example.hotelbooking.service;

import org.example.hotelbooking.common.util.PriceCalculator;
import org.example.hotelbooking.domain.Booking;
import org.example.hotelbooking.domain.BookingStatus;
import org.example.hotelbooking.domain.Room;
import org.example.hotelbooking.domain.RoomStatus;
import org.example.hotelbooking.dto.BookingResponse;
import org.example.hotelbooking.dto.CreateBookingRequest;
import org.example.hotelbooking.exception.BadRequestException;
import org.example.hotelbooking.exception.ResourceNotFoundException;
import org.example.hotelbooking.repository.BookingRepository;
import org.example.hotelbooking.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;


@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final PriceCalculator priceCalculator;

    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository, PriceCalculator priceCalculator){
        this.bookingRepository=bookingRepository;
        this.roomRepository=roomRepository;
        this.priceCalculator=priceCalculator;
    }

    @Transactional(readOnly = true)
    public BookingResponse getBookingById(String id) {
        return bookingRepository.findBookingById(id)
                .map(BookingResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id, ""));
    }

    @Transactional(readOnly = true)
    public List<BookingResponse> findBookingByCccd(String cccd){
        return bookingRepository.findBookingByCustomerCccd(cccd)
                .stream().map(BookingResponse::from)
                .toList();
    }

    @Transactional
    public BookingResponse createBooking(CreateBookingRequest request){
        Room room = roomRepository.findById(request.roomId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phòng: " + request.roomId(), "ROOM_NOT_FOUND"));

        long nights = Duration.between(request.checkInDateTime(), request.checkOutDateTime()).toDays();

        if (nights <= 0) {
            throw new BadRequestException("Ngày check-out phải sau ngày check-in ít nhất 1 ngày.");
        }

        if (nights > 30) {
            throw new BadRequestException("Hệ thống không hỗ trợ đặt phòng quá 30 ngày.");
        }
        BigDecimal totalPrice = PriceCalculator.calculate(room.getRoomType().getBasePrice(), nights);

        Booking booking = new Booking();
        booking.setCustomerName(request.customerName());
        booking.setCustomerCccd(request.customerCccd());
        booking.setRoom(room);
        booking.setCheckInDateTime(request.checkInDateTime());
        booking.setCheckOutDateTime(request.checkOutDateTime());
        booking.setNumberOfGuests(request.numberOfGuests());
        booking.setNote(request.note());
        booking.setTotalPrice(totalPrice);
        booking.setStatus(BookingStatus.PENDING); // Trạng thái ban đầu theo yêu cầu

        room.setStatus(RoomStatus.INACTIVE);

        return BookingResponse.from(bookingRepository.save(booking));
    }

    @Transactional
    public BookingResponse cancelBooking(String bookingId){
        Booking booking=bookingRepository.findBookingById(bookingId)
                .orElseThrow(()-> new ResourceNotFoundException("Không tìm thấy đơn đặt phòng: "+bookingId, "NOT_FOUND"));

        if (booking.getStatus() != BookingStatus.PENDING) {
            throw new BadRequestException("Không thể hủy đơn đặt phòng đã hoàn tất hoặc đã bị hủy trước đó.");
        }

        booking.setStatus(BookingStatus.CANCELLED);

        Room room=booking.getRoom();
        room.setStatus(RoomStatus.AVAILABLE);

        return BookingResponse.from(bookingRepository.save(booking));
    }
    // TO DO
}
