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
    public BookingResponse findById(String id){
        return BookingResponse.from(bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ma booking",id)));
    }

    @Transactional(readOnly = true)
    public List<BookingResponse> getAll() {
        return  bookingRepository.findAll()
                .stream()
                .map(BookingResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<BookingResponse> findByCustomer(String cccd){
        return bookingRepository.findByCustomerCccd(cccd)
                .stream()
                .map(BookingResponse::from)
                .toList();
    }

    @Transactional
    public BookingResponse create(CreateBookingRequest request){

        Booking booking = new Booking();

        booking.setCustomerName(request.customerName());
        booking.setCustomerCccd(request.customerCccd());
        booking.setNote(request.note());
        booking.setRoom(roomRepository.getById(request.roomId()));
        booking.setCheckInDateTime(request.checkInDateTime());
        booking.setCheckOutDateTime(request.checkOutDateTime());
        booking.setNumberOfGuests(request.numberOfGuests());
        booking.setStatus(BookingStatus.PENDING);

        return BookingResponse.from(bookingRepository.save(booking));
    }


}
