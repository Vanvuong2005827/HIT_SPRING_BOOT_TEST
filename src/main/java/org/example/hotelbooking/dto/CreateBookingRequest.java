package org.example.hotelbooking.dto;

import jakarta.validation.constraints.*;
import java.time.Instant;



public record CreateBookingRequest(
        // TO DO
        @NotBlank(message = "Tên khách hàng không được để trống")
        @Size(max = 100, message = "Tên khách hàng không được quá 100 ký tự")
        String customerName,

        // TO DO
        @NotBlank(message = "Căn cước công dân không được để trống")
        @Pattern(regexp = "^[0-9]{12}$", message = "Căn cước công dân không hợp lệ (phải bao gồm đúng 12 chữ số)")
        String customerCccd,

        // TO DO
        @NotBlank(message = "Id phòng không được để trống")
        String roomId,

        // TO DO
        @NotNull(message = "Giờ check-in không được để trống")
        @FutureOrPresent(message = "Thời gian check-in phải từ thời điểm hiện tại trở đi")
        Instant checkInDateTime,

        // TO DO
        @NotNull(message = "Giờ check-out không được để trống")
        @Future(message = "Thời gian check-out phải ở tương lai")
        Instant checkOutDateTime,

        // TO DO
        @NotNull(message = "Số lượng khách không được để trống")
        @Min(value = 1, message = "Số lượng khách tối thiểu phải là 1")
        Integer numberOfGuests,

        // TO DO
        @Size(max = 500, message = "Ghi chú không được vượt quá 500 ký tự")
        String note
) {
}
