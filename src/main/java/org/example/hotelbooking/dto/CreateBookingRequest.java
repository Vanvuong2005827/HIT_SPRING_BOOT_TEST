package org.example.hotelbooking.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hotelbooking.constant.ErrorMessage;

import java.time.Instant;



public record CreateBookingRequest(
        // TO DO
        @NotNull(message = "Tên khách hàng không được để trống")

        String customerName,

        // TO DO
        @NotNull(message = "Căn cước công dân đéo được để trống")
        String customerCccd,

        // TO DO
        @NotNull(message = "Id phòng không được để trống")
        String roomId,

        // TO DO
        @NotNull(message = "Giờ check in không được để trống")
        Instant checkInDateTime,

        // TO DO
        @NotNull(message = "Giờ check out không được để trống")
        Instant checkOutDateTime,

        // TO DO
        @NotNull(message = "Số lượng khách không được để trống")
        Integer numberOfGuests,

        // TO DO
        String note
) {
}
