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
    private final RoomRepository roomRepository;

    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional(readOnly = true)
    public BookingResponse getBooking(String id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", id));
        return BookingResponse.from(booking);
    }

    @Transactional(readOnly = true)
    public List<BookingResponse> getBookingsByCustomer(String cccd) {
        return bookingRepository.findByCustomerCccd(cccd)
                .stream()
                .map(BookingResponse::from)
                .toList();
    }

    @Transactional
    public BookingResponse createBooking(CreateBookingRequest request) {
        if (!request.checkInDateTime().isBefore(request.checkOutDateTime())) {
            throw new BadRequestException(ErrorMessage.INVALID_DATES);
        }

        long days = Duration.between(request.checkInDateTime(), request.checkOutDateTime()).toDays();
        if (days == 0) days = 1;

        if (days > 30) {
            throw new BadRequestException(ErrorMessage.RENTAL_PERIOD_TOO_LONG);
        }

        Room room = roomRepository.findById(request.roomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room", request.roomId()));

        if (room.getStatus() != RoomStatus.AVAILABLE) {
            throw new ConflictException(ErrorMessage.ROOM_NOT_AVAILABLE);
        }

        if (request.numberOfGuests() > room.getRoomType().getMaxOccupancy()) {
            throw new BadRequestException(ErrorMessage.GUESTS_EXCEED_CAPACITY);
        }

        long overlaps = bookingRepository.countOverlappingBookings(
                room.getId(), request.checkInDateTime(), request.checkOutDateTime());
        if (overlaps > 0) {
            throw new ConflictException(ErrorMessage.ROOM_NOT_AVAILABLE);
        }

        Booking booking = new Booking();
        booking.setCustomerName(request.customerName());
        booking.setCustomerCccd(request.customerCccd());
        booking.setRoom(room);
        booking.setCheckInDateTime(request.checkInDateTime());
        booking.setCheckOutDateTime(request.checkOutDateTime());
        booking.setNumberOfGuests(request.numberOfGuests());
        booking.setNote(request.note());
        booking.setTotalPrice(PriceCalculator.calculate(room.getRoomType().getBasePrice(), days));

        booking = bookingRepository.save(booking);
        return BookingResponse.from(booking);
    }

    @Transactional
    public BookingResponse cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", bookingId));

        if (booking.getStatus() != BookingStatus.PENDING) {
            throw new ConflictException(ErrorMessage.BOOKING_NOT_PENDING);
        }

        booking.setStatus(BookingStatus.CANCELLED);
        booking = bookingRepository.save(booking);
        return BookingResponse.from(booking);
    }
}