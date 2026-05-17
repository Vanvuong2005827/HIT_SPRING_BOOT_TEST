package org.example.hotelbooking.controller;

import jakarta.validation.Valid;
import org.example.hotelbooking.common.response.ApiResponse;
import org.example.hotelbooking.common.response.ListResponse;
import org.example.hotelbooking.constant.ApiPath;
import org.example.hotelbooking.dto.BookingResponse;
import org.example.hotelbooking.dto.CreateBookingRequest;
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

    @GetMapping("/booking/{id}")
    public ApiResponse<BookingResponse> getBookingById(
            @PathVariable String id
    ) {
        return ApiResponse.ok("success",bookingService.getBookingById(id));
    }

    @GetMapping("/bookings")
    public ApiResponse<ListResponse<BookingResponse>> getBookingsByCustomer(
            @RequestParam String cccd
    ) {
        List<BookingResponse> responses = bookingService.getBookingsByCCCD(cccd);
        return ApiResponse.ok("success",ListResponse.of(responses));
    }

    @PostMapping("/booking")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<BookingResponse> createBooking(@Valid @RequestBody CreateBookingRequest request) {
        BookingResponse bookingResponse = bookingService.createBooking(request);
        return ApiResponse.ok("success",bookingResponse);
    }

    @PatchMapping("/booking/{booking_id}/cancel")
    public ApiResponse<BookingResponse> cancelBooking(
            @PathVariable("booking_id") String id
    ) {
        return ApiResponse.ok("success",bookingService.cancelBooking(id));
    }
}
