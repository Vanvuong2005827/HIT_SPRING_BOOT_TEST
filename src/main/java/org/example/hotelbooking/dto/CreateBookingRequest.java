package org.example.hotelbooking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.hotelbooking.constant.ErrorMessage;

import java.time.Instant;

public record CreateBookingRequest(
        @NotBlank(message = "Tên khách hàng không được để trống")
        String customerName,

        @NotBlank(message = "CCCD không được để trống")
        String customerCccd,

        @NotBlank(message = "ID không được để trống")
        String roomId,

        @NotNull(message = "Thời gian check-in không được để trống")
        Instant checkInDateTime,

        @NotNull(message = "Thời gian check-out không được để trống")
        Instant checkOutDateTime,

        @NotNull(message = "Số lượng khách không được để trống")
        @Min(value = 1, message = "Số lượng khách phải từ 1 trở lên")
        Integer numberOfGuests,

        String note
) {
}
