package org.example.hotelbooking.service;

import org.example.hotelbooking.common.util.PriceCalculator;
import org.example.hotelbooking.constant.ErrorMessage;
import org.example.hotelbooking.domain.Booking;
import org.example.hotelbooking.domain.BookingStatus;
import org.example.hotelbooking.domain.Room;
import org.example.hotelbooking.domain.RoomStatus;
import org.example.hotelbooking.dto.BookingResponse;
import org.example.hotelbooking.dto.CreateBookingRequest;
import org.example.hotelbooking.dto.RoomResponse;
import org.example.hotelbooking.exception.BadRequestException;
import org.example.hotelbooking.exception.ConflictException;
import org.example.hotelbooking.exception.ResourceNotFoundException;
import org.example.hotelbooking.repository.BookingRepository;
import org.example.hotelbooking.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
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
    public List<BookingResponse> findAll(){
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingResponse> bookingResponses = new ArrayList<>();
        for (Booking b : bookings){
            bookingResponses.add(BookingResponse.from(b));
        }
        return bookingResponses;
    }

    @Transactional(readOnly = true)
    public BookingResponse findById(String id){
        return bookingRepository.findById(id)
                .map(BookingResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("Id", id));
    }

    @Transactional(readOnly = true)
    public List<BookingResponse> findByCustomerCccd(String cccd){
        List<Booking> bookings = bookingRepository.findByCustomerCccd(cccd);
        List<BookingResponse> bookingResponses = new ArrayList<>();
        for (Booking b : bookings)
            bookingResponses.add(BookingResponse.from(b));

        return bookingResponses;
    }

    @Transactional(readOnly = true)
    public BookingResponse create(CreateBookingRequest createBookingRequest){
        Booking booking = new Booking();

        booking.setCustomerName(createBookingRequest.customerName());
        booking.setCustomerCccd(createBookingRequest.customerCccd());
        booking.setCheckInDateTime(createBookingRequest.checkInDateTime());
        booking.setCheckOutDateTime(createBookingRequest.checkOutDateTime());
        booking.setNumberOfGuests(createBookingRequest.numberOfGuests());
        booking.setNote(createBookingRequest.note());

        return BookingResponse.from(booking);
    }

    @Transactional(readOnly = true)
    public void delete(String id){
        if (!roomRepository.existsById(id))
            throw new ResourceNotFoundException("Id", id);

        roomRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean checkPending(String id){
        Optical<Booking> booking = bookingRepository.findById(id);

    }
}
