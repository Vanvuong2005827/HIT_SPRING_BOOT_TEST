package org.example.hotelbooking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.hotelbooking.constant.ErrorMessage;
import org.example.hotelbooking.domain.Booking;

import java.time.Instant;

public record CreateBookingRequest(
        // TO DO
        String customerName,

        // TO DO
        String customerCccd,

        // TO DO
        String roomId,

        // TO DO
        Instant checkInDateTime,

        // TO DO
        Instant checkOutDateTime,

        // TO DO
        Integer numberOfGuests,

        // TO DO
        String note
) {
    public static CreateBookingRequest from(Booking booking){
        return new CreateBookingRequest(
                booking.getCustomerName(),
                booking.getCustomerCccd(),
                booking.getRoom().getId(),
                booking.getCheckInDateTime(),
                booking.getCheckOutDateTime(),
                booking.getNumberOfGuests(),
                booking.getNote()
        );
    }
}
