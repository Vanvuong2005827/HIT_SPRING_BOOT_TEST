package org.example.hotelbooking.service;

import org.example.hotelbooking.common.util.PriceCalculator;
import org.example.hotelbooking.domain.Booking;
import org.example.hotelbooking.domain.BookingStatus;
import org.example.hotelbooking.domain.Room;
import org.example.hotelbooking.domain.RoomStatus;
import org.example.hotelbooking.dto.BookingResponse;
import org.example.hotelbooking.dto.CreateBookingRequest;
import org.example.hotelbooking.exception.ResourceNotFoundException;
import org.example.hotelbooking.repository.BookingRepository;
import org.example.hotelbooking.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {

    // TO DO
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional(readOnly = true)
    public BookingResponse findBookingById(String id) {
        return BookingResponse.from(bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ma booking", id)));
    }

    @Transactional(readOnly = true)
    public List<BookingResponse> findByCustomerByCccd(String cccd) {
        return bookingRepository.findByCustomerCccd(cccd)
                .stream()
                .map(BookingResponse::from)
                .toList();
    }

    @Transactional
    public BookingResponse createBooking(CreateBookingRequest request) {

        Room room = roomRepository.findById(request.roomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room", request.roomId()));

        if(room.getStatus() != RoomStatus.AVAILABLE){
            throw new IllegalStateException("Phong khong the dat");
        }

        if(request.numberOfGuests() > room.getRoomType().getMaxOccupancy()){
            throw new IllegalArgumentException("So luong khach nhieu hon suc chua");
        }

        long nighs = ChronoUnit.DAYS.between(request.checkInDateTime(), request.checkOutDateTime());
        if(nighs<1){
            nighs=1;
        }

        BigDecimal pricePerNight = room.getRoomType().getBasePrice();
        BigDecimal totalPrice = PriceCalculator.calculate(pricePerNight, nighs);


        Booking booking = new Booking();

        booking.setCustomerName(request.customerName());
        booking.setCustomerCccd(request.customerCccd());
        booking.setNote(request.note());
        booking.setRoom(room);
        booking.setCheckInDateTime(request.checkInDateTime());
        booking.setCheckOutDateTime(request.checkOutDateTime());
        booking.setNumberOfGuests(request.numberOfGuests());
        booking.setTotalPrice(totalPrice);
        booking.setStatus(BookingStatus.PENDING);

        return BookingResponse.from(bookingRepository.save(booking));
    }


    @Transactional
    public BookingResponse cancelBooking(String id){
        Booking booking=bookingRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("booking",id));

        if(booking.getStatus() == BookingStatus.CANCELLED){
            throw new IllegalStateException("Phong khong the huy");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        return BookingResponse.from(bookingRepository.save(booking));
    }
}
