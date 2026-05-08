package org.example.hotelbooking.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

public record AvailableRoomsRequest(
        @NotBlank(message = "gày checkin không được để trống")
        Instant checkInDateTime,

        @NotBlank(message = "gày check không được để trống")
        Instant checkOutDateTime
) {
}
