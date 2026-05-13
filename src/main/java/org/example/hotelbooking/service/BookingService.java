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
import org.example.hotelbooking.exception.ValidationException;
import org.example.hotelbooking.repository.BookingRepository;
import org.example.hotelbooking.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional(readOnly = true)
    public List<BookingResponse> findAllBooking() {
        return bookingRepository.findAll().stream()
                .map(BookingResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public BookingResponse findBookingById(String id) {
        return bookingRepository.findById(id)
                .map(BookingResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("Id", id));
    }

    @Transactional(readOnly = true)
    public List<BookingResponse> findBookingByCustomerCccd(String cccd) {
        return bookingRepository.findByCustomerCccd(cccd).stream()
                .map(BookingResponse::from)
                .toList();
    }

    @Transactional
    public BookingResponse createBooking(CreateBookingRequest createBookingRequest) {
        Optional<Room> room = roomRepository.findById(createBookingRequest.roomId());
        if (room.isEmpty())
            throw new ResourceNotFoundException("RoomId", createBookingRequest.roomId());

        Booking booking = Booking.builder()
                .customerName(createBookingRequest.customerName())
                .customerCccd(createBookingRequest.customerCccd())
                .room(room.get())
                .checkInDateTime(createBookingRequest.checkInDateTime())
                .checkOutDateTime(createBookingRequest.checkOutDateTime())
                .numberOfGuests(createBookingRequest.numberOfGuests())
                .note(createBookingRequest.note())
                .build();

        if (booking.getNote() != null && booking.getNote().isBlank())
            throw new ValidationException("Note có thể null nhưng không thể chỉ chứa khoảng trắng");
        if (booking.getCheckInDateTime().isAfter(booking.getCheckOutDateTime()))
            throw new ValidationException("Thời gian nhận phòng phải nhỏ hơn thời gian trả phòng");
        if (booking.getNumberOfGuests() <= 0)
            throw new ValidationException("Số khách trong 1 phòng phải là số dương");
        if (room.get().getStatus().equals(RoomStatus.INACTIVE))
            throw new BadRequestException("Phòng không còn hoạt động");

        return BookingResponse.from(booking);
    }

    @Transactional
    public void deleteBookingById(String id) {
        if (!bookingRepository.existsById(id))
            throw new ResourceNotFoundException("Id", id);

        bookingRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean checkPending(String id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isEmpty())
            throw new ResourceNotFoundException("Id", id);

        return booking.get().getStatus().equals(BookingStatus.PENDING);
    }

    @Transactional
    public boolean cancelBooking(String id){
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isEmpty())
            throw new ResourceNotFoundException("Id", id);

        if (booking.get().getStatus().equals(BookingStatus.PENDING)){
            booking.get().setStatus(BookingStatus.CANCELLED);
            bookingRepository.save(booking.get());
            return true;
        }
        else return false;
    }
}
