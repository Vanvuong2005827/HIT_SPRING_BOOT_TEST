package org.example.hotelbooking.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.example.hotelbooking.constant.ErrorMessage;

import java.time.Instant;

public record AvailableRoomsRequest(
        @NotNull(message = "Thoi gian check-in khong duoc de trong")
        @FutureOrPresent(message = "Thoi gian nhan phong phai la thoi diem hien tai")
        Instant checkInDateTime,

        @NotNull(message = "Thoi gian check-out khong duoc de trong")
        @Future(message = "Thoi gian nhan phong phai la tuong lai")
        Instant checkOutDateTime
) {
}
