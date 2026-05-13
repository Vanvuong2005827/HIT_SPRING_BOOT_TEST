package org.example.hotelbooking.dto;

import jakarta.validation.constraints.*;
import org.example.hotelbooking.constant.ErrorMessage;
<<<<<<< HEAD
=======
import org.example.hotelbooking.domain.Booking;
import org.example.hotelbooking.exception.BadRequestException;
>>>>>>> e18bc9b (fix: repair my project)

import java.time.Instant;

public record CreateBookingRequest(
        @NotBlank(message = "Tên khách hàng không được để trống")
        String customerName,

        @NotBlank(message = "Căn cước công dân của khách hàng không được để trống")
        String customerCccd,

        @NotBlank(message = "Id phòng không được để trống")
        String roomId,

        @NotBlank(message = "Thời gian nhận phòng không được để trống")
        Instant checkInDateTime,

        Instant checkOutDateTime,

        @NotBlank(message = "Số lượng khách không được để trống")
        @PositiveOrZero(message = "Sô lượng khách phải là số không âm")
        Integer numberOfGuests,

        String note
) {
<<<<<<< HEAD
=======
    public static CreateBookingRequest from(Booking booking){
        if (booking.getCheckOutDateTime() != null && booking.getCheckInDateTime().isAfter(booking.getCheckOutDateTime()))
            throw new BadRequestException("Thời gian nhận phòng phải nhỏ hơn thời gian trả phòng");

        return new CreateBookingRequest(
                booking.getCustomerName(),
                booking.getCustomerCccd(),
                booking.getRoom().getId(),
                booking.getCheckInDateTime(),
                booking.getCheckOutDateTime(),
                booking.getNumberOfGuests(),
                booking.getNote()
        );
    }
>>>>>>> e18bc9b (fix: repair my project)
}
