package org.example.hotelbooking.service;

import org.example.hotelbooking.common.util.PriceCalculator;
import org.example.hotelbooking.constant.ErrorMessage;
import org.example.hotelbooking.domain.Booking;
import org.example.hotelbooking.domain.BookingStatus;
import org.example.hotelbooking.domain.Room;
import org.example.hotelbooking.domain.RoomStatus;
import org.example.hotelbooking.dto.BookingResponse;
import org.example.hotelbooking.dto.CreateBookingRequest;
import org.example.hotelbooking.exception.BadRequestException;
import org.example.hotelbooking.exception.ConflictException;
import org.example.hotelbooking.exception.ResourceNotFoundException;
import org.example.hotelbooking.repository.BookingRepository;
import org.example.hotelbooking.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;


    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional(readOnly = true)
    public BookingResponse getBooking(String id) {
        return bookingRepository.findById(id)
                .map(BookingResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "Id"));
    }

    public BookingResponse createBooking(CreateBookingRequest request) {
        BookingResponse booking = BookingResponse.builder()
                .customerName(request.customerName())
                .customerCccd(request.customerCccd())
                .roomId(request.roomId())
                .checkInDateTime(request.checkInDateTime())
                .checkOutDateTime(request.checkOutDateTime())
                .numberOfGuests(request.numberOfGuests())
                .status(BookingStatus.PENDING)
                .note(request.note())
                .build();

        return booking;
    }
}
