package org.example.hotelbooking.service;

//import org.example.hotelbooking.common.util.PriceCalculator;
//import org.example.hotelbooking.constant.ErrorMessage;
//import org.example.hotelbooking.domain.Booking;
//import org.example.hotelbooking.domain.BookingStatus;
//import org.example.hotelbooking.domain.Room;
//import org.example.hotelbooking.domain.RoomStatus;
//import org.example.hotelbooking.dto.BookingResponse;
//import org.example.hotelbooking.dto.CreateBookingRequest;
//import org.example.hotelbooking.exception.BadRequestException;
//import org.example.hotelbooking.exception.ConflictException;
//import org.example.hotelbooking.exception.ResourceNotFoundException;
//import org.example.hotelbooking.repository.BookingRepository;
//import org.example.hotelbooking.repository.RoomRepository;

import org.example.hotelbooking.domain.Booking;
import org.example.hotelbooking.dto.BookingResponse;
import org.example.hotelbooking.dto.CreateBookingRequest;
import org.example.hotelbooking.exception.BadRequestException;
import org.example.hotelbooking.repository.BookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public BookingResponse create(CreateBookingRequest createBookingRequest) {
        Booking booking = bookingRepository.findById(createBookingRequest.roomId())
                .orElseThrow(() -> new BadRequestException("Lỗi"));
    Booking newBooking = new Booking();
    booking.setCustomerName(createBookingRequest.customerName());
    booking.setCustomerCccd(createBookingRequest.customerCccd());
    booking.setCheckInDateTime(createBookingRequest.checkInDateTime());
    booking.setCheckOutDateTime(createBookingRequest.checkOutDateTime());
    booking.setNumberOfGuests(createBookingRequest.numberOfGuests());
    booking.setNote(createBookingRequest.note());


    return BookingResponse.from(bookingRepository.save(booking));
    }

    @Transactional(readOnly = true)
    public BookingResponse findById(String id) {
        return getBookingById(id);
    }

    private BookingResponse getBookingById(String id) {
        return bookingRepository.findById(id)
                .map(BookingResponse::from)
                .orElseThrow(() -> new BadRequestException( "Không tìm thấy  với ID: " + id));
    }


    @Transactional
    public void delete(String id) {
        bookingRepository.deleteById(id);
    }



    @Transactional
    public BookingResponse update(String id, CreateBookingRequest createBookingRequest) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BadRequestException( "Không tìm thấy  với ID: " + id));
        booking.setCustomerName(createBookingRequest.customerName());
        booking.setCustomerCccd(createBookingRequest.customerCccd());
        booking.setCheckInDateTime(createBookingRequest.checkInDateTime());
        booking.setCheckOutDateTime(createBookingRequest.checkOutDateTime());
        booking.setNumberOfGuests(createBookingRequest.numberOfGuests());
        booking.setNote(createBookingRequest.note());

        return BookingResponse.from(bookingRepository.save(booking));

    }




}
