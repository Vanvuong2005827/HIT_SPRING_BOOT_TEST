package org.example.hotelbooking.dto;

import jakarta.validation.constraints.NotNull;
import org.example.hotelbooking.constant.ErrorMessage;

import java.time.Instant;

public record AvailableRoomsRequest(
        @NotNull(message = "Vui lòng nhập thời gian check0in")
        Instant checkInDateTime,

        @NotNull(message = "Vui lòng nhập thời gian check-out")
        Instant checkOutDateTime
) {
}
