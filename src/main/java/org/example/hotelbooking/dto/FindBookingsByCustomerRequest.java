package org.example.hotelbooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record FindBookingsByCustomerRequest(
        @NotBlank(message = "CCCD không được để trống")
        @Pattern(regexp = "^\\d{12}$", message = "CCCD không hợp lệ (phải bao gồm đúng 12 chữ số)")
        String cccd
) {
}