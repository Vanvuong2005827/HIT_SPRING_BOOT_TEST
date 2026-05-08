package org.example.hotelbooking.dto;

import org.example.hotelbooking.domain.Booking;
import org.example.hotelbooking.domain.BookingStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record BookingResponse(
        String id,
        String customerName,
        String customerCccd,
        String roomId,
        Instant checkInDateTime,
        Instant checkOutDateTime,
        Integer numberOfGuests,
        BookingStatus status,
        BigDecimal totalPrice,
        String note,
        Instant createdAt,
        Instant updatedAt
) {

    public static BookingResponse from(Booking booking) {
        return new BookingResponse(
                booking.getId(),
                booking.getCustomerName(),
                booking.getCustomerCccd(),
                booking.getRoom().getId(),
                booking.getCheckInDateTime(),
                booking.getCheckOutDateTime(),
                booking.getNumberOfGuests(),
                booking.getStatus(),
                booking.getTotalPrice(),
                booking.getNote(),
                booking.getCreatedAt(),
                booking.getUpdatedAt()
        );
    }
//    public static Booking from(BookingResponse response) {
//        return new Booking(
//                response.id(),
//                response.customerName(),
//                response.customerCccd(),
//                response.roomId(),
//                response.checkInDateTime(),
//                response.checkOutDateTime(),
//                response.numberOfGuests(),
//                response.status(),
//                response.totalPrice(),
//                response.note(),
//                response.createdAt(),
//                response.updatedAt()
//        );
//    }
}
