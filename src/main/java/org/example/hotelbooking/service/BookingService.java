package org.example.hotelbooking.service;

import org.example.hotelbooking.common.response.ListResponse;
import org.example.hotelbooking.common.util.PriceCalculator;
import org.example.hotelbooking.domain.Booking;
import org.example.hotelbooking.dto.BookingResponse;
import org.example.hotelbooking.dto.CreateBookingRequest;
import org.example.hotelbooking.dto.RoomResponse;
import org.example.hotelbooking.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.example.hotelbooking.domain.BookingStatus.CANCELLED;
import static org.example.hotelbooking.domain.RoomStatus.INACTIVE;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public Booking create(CreateBookingRequest request) {
        Booking order = new Booking();
        order.setCustomerName(request.customerName());
        order.setCustomerCccd(request.customerCccd());
        order.setNote(request.note());
        order.setCheckInDateTime(request.checkInDateTime());
        order.setCheckOutDateTime(request.checkOutDateTime());
        order.setNumberOfGuests(request.numberOfGuests());
        order.setTotalPrice(PriceCalculator.calculate(BigDecimal.valueOf(100000), ChronoUnit.DAYS.between(request.checkInDateTime(),request.checkOutDateTime())));
        bookingRepository.save(order);
        return order;
    }
    @Transactional(readOnly = true)
    public BookingResponse getBooking(String id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy booking với ID: " + id));
        return BookingResponse.from(booking);
    }
    @Transactional
    public List<BookingResponse> getBookingsByCCCD(String cccd){
        return bookingRepository.findBookingByCccd(cccd).stream()
                .map(BookingResponse::from)
                .toList();
    }
    @Transactional
    public BookingResponse cancelBooking(String id){
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy booking với ID: " + id));
        booking.setStatus(CANCELLED);
        bookingRepository.save(booking);
        return BookingResponse.from(booking);
    }
    // TO DO
}
