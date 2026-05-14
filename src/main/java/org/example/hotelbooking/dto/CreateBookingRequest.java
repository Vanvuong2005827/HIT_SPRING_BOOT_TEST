package org.example.hotelbooking.dto;

import jakarta.validation.constraints.*;
import org.example.hotelbooking.constant.ErrorMessage;

import java.time.Instant;


public record CreateBookingRequest(
        @NotBlank(message = "Ten khach hang khong duoc de trong")
        @Size(min = 2,max = 120,message = "Ten khach hang phai tu 2-120 ky tu")
        String customerName,

        @NotBlank(message = "CCCD khong duoc de trong")
        String customerCccd,

        @NotBlank(message = "Ma phong khong duoc de trong")
        String roomId,

        @NotNull(message = "Thoi gian check-in khong duoc de trong")
        @FutureOrPresent(message = "Thoi gian nhan phong phai la thoi diem hien tai")
        Instant checkInDateTime,

        @NotNull(message = "Thoi gian check-out khong duoc de trong")
        @Future(message = "Thoi gian nhan phong phai la tuong lai")
        Instant checkOutDateTime,

        @NotNull(message = "So luong khach hang khong de de trong")
        @Min(value = 1,message = "So luong khach hang phai >= 1")
        Integer numberOfGuests,

        @Size(max = 1000,message = "Ghi chu khong qua 1000 ky tu")
        String note

) {
}
