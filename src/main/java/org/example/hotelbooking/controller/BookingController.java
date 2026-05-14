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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(ApiPath.API_V1)
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("booking/{id}")
    public ApiResponse<BookingResponse> getBookingById(
            @PathVariable("id") String id
    ) {
        return ApiResponse.ok("Success",bookingService.findBookingById(id));
    }

    @GetMapping("/bookings/{cccd}")
    public ApiResponse<ListResponse<BookingResponse>> getBookingsByCustomer(
        @PathVariable("cccd") String cccd
    ) {
        return ApiResponse.ok("Success", ListResponse.of(bookingService.findByCustomerByCccd(cccd)));
    }

    @PostMapping("/booking")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<BookingResponse> createBooking(
            @Valid @RequestBody CreateBookingRequest request
    ) {
        return ApiResponse.ok("Created successfully!",bookingService.createBooking(request));
    }

    @PatchMapping("/booking/{booking_id}/cancel")
    public ApiResponse<BookingResponse> cancelBooking(
            @PathVariable String booking_id
    ) {
        return ApiResponse.ok("Updated successfully!",bookingService.cancelBooking(booking_id));
    }
}
