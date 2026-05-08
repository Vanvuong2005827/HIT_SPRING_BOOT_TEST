package org.example.hotelbooking.dto.request;

import jakarta.validation.constraints.*;

import java.time.Instant;

public record CreateBookingRequest(
        @NotBlank(message = "Tên không được để trống")
        @Size(min = 2, max = 50, message = "Tên phải từ 2 đến 50 ký tự")
        String customerName,

        @NotBlank(message = "Căn cước công dân phải 12 ")
        @Pattern(regexp = "^\\d{12}$")
        String customerCccd,

        @NotBlank(message = "Id phòng không được để ")
        String roomId,

        @NotBlank(message = "Ngày checkin không được để trống")
        Instant checkInDateTime,

        @NotBlank(message = "Ngày checkout không được để trống")
        Instant checkOutDateTime,

        @NotBlank(message = "Số lượng khách book không được để trống")
        Integer numberOfGuests,

        @NotNull(message = "Note không được để null")
        String note
) {
}
