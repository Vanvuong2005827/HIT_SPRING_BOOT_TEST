package org.example.hotelbooking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record FindBookingsByCustomerRequest(
        @NotBlank(message = "CCCD không được để trống")
        @Pattern(regexp = "^\\d{12}$")
        String cccd
) {
}
