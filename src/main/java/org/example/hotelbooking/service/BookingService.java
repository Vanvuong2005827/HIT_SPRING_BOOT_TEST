package org.example.hotelbooking.service;

import org.example.hotelbooking.common.response.ListResponse;
import org.example.hotelbooking.domain.Booking;
import org.example.hotelbooking.domain.BookingStatus;
import org.example.hotelbooking.dto.request.CreateBookingRequest;
import org.example.hotelbooking.dto.request.FindBookingsByCustomerRequest;
import org.example.hotelbooking.dto.respone.BookingResponse;
import org.example.hotelbooking.repository.BookingRepository;
import org.example.hotelbooking.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    public BookingResponse findBookingById(String id) {
        return BookingResponse.from(bookingRepository.getBookingById(id));
    }

    public List<BookingResponse> findAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(BookingResponse::from)
                .toList();
    }

    public BookingResponse create(CreateBookingRequest rq) {
        Booking booking = new Booking();
        booking.setCustomerName(rq.customerName());
        booking.setCustomerCccd(rq.customerCccd());
        booking.setRoom(roomRepository.getRoomById(rq.roomId()));
        booking.setCheckInDateTime(rq.checkInDateTime());
        booking.setCheckOutDateTime(rq.checkOutDateTime());
        booking.setNumberOfGuests(rq.numberOfGuests());
        booking.setNote(rq.note());

        bookingRepository.save(booking);
        return BookingResponse.from(booking);
    }

    public List<BookingResponse> findAllBookingsByCustomerCccd(String cccd) {
        return bookingRepository.getBookingByCustomerCccd(cccd)
                .stream()
                .map(BookingResponse::from)
                .toList();
    }

    public BookingResponse cancelBooking(String id) {
        Booking booking = bookingRepository.getBookingById(id);

        booking.setStatus(BookingStatus.CANCELLED);

        return BookingResponse.from(booking);
    }
}
