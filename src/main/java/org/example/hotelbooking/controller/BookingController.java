package org.example.hotelbooking.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.hotelbooking.common.response.ApiResponse;
import org.example.hotelbooking.common.response.ListResponse;
import org.example.hotelbooking.constant.ApiPath;
import org.example.hotelbooking.constant.SuccessMessage;
import org.example.hotelbooking.dto.BookingResponse;
import org.example.hotelbooking.dto.CreateBookingRequest;
import org.example.hotelbooking.dto.FindBookingsByCustomerRequest;
import org.example.hotelbooking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse<BookingResponse> getBookingById(
            @RequestParam("booking_id") @NotBlank String bookingId
    ) {
        BookingResponse response = bookingService.getBookingById(bookingId);
        return ApiResponse.ok(SuccessMessage.GET_SUCCESS, response);
    }

    @GetMapping("/bookings")
    public ApiResponse<ListResponse<BookingResponse>> getBookingsByCustomer(
            @Valid FindBookingsByCustomerRequest request
    ) {
        List<BookingResponse> responses = bookingService.getBookingsByCustomer(request.cccd());
        return ApiResponse.ok(SuccessMessage.GET_SUCCESS, ListResponse.of(responses));
    }

    @PostMapping("/booking")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<BookingResponse> createBooking(
            @RequestBody @Valid CreateBookingRequest request
    ) {
        BookingResponse response = bookingService.createBooking(request);
        return ApiResponse.created(SuccessMessage.CREATE_SUCCESS, response);
    }

    @PatchMapping("/booking/{booking_id}/cancel")
    public ApiResponse<BookingResponse> cancelBooking(
            @PathVariable("booking_id") String bookingId
    ) {
        BookingResponse response = bookingService.cancelBooking(bookingId);
        return ApiResponse.ok(SuccessMessage.CANCEL_SUCCESS, response);
    }
}