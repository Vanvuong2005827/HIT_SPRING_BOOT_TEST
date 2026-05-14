package org.example.hotelbooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.example.hotelbooking.constant.ErrorMessage;

public record FindBookingsByCustomerRequest(
        @NotBlank(message = "CCCD khong duoc de trong")
        String cccd
) {
}
