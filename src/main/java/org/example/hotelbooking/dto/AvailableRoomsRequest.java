package org.example.hotelbooking.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record AvailableRoomsRequest(
        @NotNull(message = "Thời gian check-in không được để trống")
        @FutureOrPresent(message = "Thời gian check-in phải từ thời điểm hiện tại trở đi")
        Instant checkInDateTime,

        // TO DO
        @NotNull(message = "Thời gian check-out không được để trống")
        Instant checkOutDateTime
) {
    public AvailableRoomsRequest {
        if (checkInDateTime != null && checkOutDateTime != null) {
            if (!checkOutDateTime.isAfter(checkInDateTime)) {
                throw new IllegalArgumentException("Thời gian check-out phải diễn ra sau check-in.");
            }
        }
    }
}
