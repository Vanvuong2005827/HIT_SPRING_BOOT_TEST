package org.example.hotelbooking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.hotelbooking.constant.ErrorMessage;

import java.time.Instant;

public record CreateBookingRequest(
        @NotBlank(message = "Ten khach hang khong duoc de trong")
        String customerName,

        // TO DO
        @NotBlank(message = "CCCD khong duoc de trong")
        String customerCccd,

        // TO DO
        @NotBlank(message = "roomId khong duoc de trong")
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
}
