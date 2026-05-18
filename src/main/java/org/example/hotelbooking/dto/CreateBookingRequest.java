package org.example.hotelbooking.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.Instant;

public record CreateBookingRequest(
        @NotBlank(message = "Tên khách hàng không được để trống")
        String customerName,

        @NotBlank(message = "CCCD không được để trống")
        @Pattern(regexp = "^\\d{12}$", message = "CCCD không hợp lệ (phải bao gồm đúng 12 chữ số)")
        String customerCccd,

        @NotBlank(message = "ID không được để trống")
        String roomId,

        @NotNull(message = "Thời gian check-in không được để trống")
        Instant checkInDateTime,

        @NotNull(message = "Thời gian check-out không được để trống")
        Instant checkOutDateTime,

        @NotNull(message = "Số lượng khách không được để trống")
        @Min(value = 1, message = "Số lượng khách phải từ 1 trở lên")
        @Max(value = 10, message = "Số lượng khách vượt quá số lượng tối đa hệ thống cho phép")
        Integer numberOfGuests,

        String note
) {
}