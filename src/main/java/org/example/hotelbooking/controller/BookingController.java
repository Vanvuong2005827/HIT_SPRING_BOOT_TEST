package org.example.hotelbooking.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.hotelbooking.common.response.ApiResponse;
import org.example.hotelbooking.common.response.ListResponse;
import org.example.hotelbooking.constant.ApiPath;
import org.example.hotelbooking.constant.ErrorMessage;
import org.example.hotelbooking.constant.SuccessMessage;
import org.example.hotelbooking.domain.Booking;
import org.example.hotelbooking.dto.BookingResponse;
import org.example.hotelbooking.dto.CreateBookingRequest;
import org.example.hotelbooking.dto.FindBookingsByCustomerRequest;
import org.example.hotelbooking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping(ApiPath.API_V1)
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/booking")
    public ApiResponse<BookingResponse> getBooking(
    ) {
//        Bookin
        return null;
    }

    @GetMapping("/bookings")
    public ApiResponse<ListResponse<BookingResponse>> getBookingsByCustomer(
    ) {return null;
    }

    @PostMapping("/booking")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<BookingResponse> createBooking() {

        return null;
    }

    @PatchMapping("/booking/{booking_id}/cancel")
    public ApiResponse<BookingResponse> cancelBooking(
    ) {return null;
    }
}
