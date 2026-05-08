package org.example.hotelbooking.controller;

import org.example.hotelbooking.common.response.ApiResponse;
import org.example.hotelbooking.common.response.ListResponse;
import org.example.hotelbooking.constant.ApiPath;
import org.example.hotelbooking.dto.request.CreateBookingRequest;
import org.example.hotelbooking.dto.request.FindBookingsByCustomerRequest;
import org.example.hotelbooking.dto.respone.BookingResponse;
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

    @GetMapping("/booking/{id}")
    public ApiResponse<BookingResponse> getBooking(
            @PathVariable String id
    ) {
        BookingResponse booking = bookingService.findBookingById(id);
        return ApiResponse.ok("Success!", booking);
    }

    @GetMapping("/bookings")
    public ApiResponse<ListResponse<BookingResponse>> getBookingsByCustomer(
    ) {
        List<BookingResponse> items = bookingService.findAllBookings();
        return ApiResponse.ok("Success!", ListResponse.of(items));
    }

    @PostMapping("/booking")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<BookingResponse> createBooking(@RequestBody CreateBookingRequest rq) {
        BookingResponse item = bookingService.create(rq);
        return ApiResponse.ok("Created!", item);
    }

    @PatchMapping("/booking/{booking_id}/cancel")
    public ApiResponse<BookingResponse> cancelBooking(
            @PathVariable String booking_id
    ) {
        BookingResponse item = bookingService.cancelBooking(booking_id);
        return ApiResponse.ok("Patched!", item);
    }

    @GetMapping("/bookings/{cccd}")
    public ApiResponse<ListResponse<BookingResponse>> getAllBookingsByCCCD(
            @PathVariable String cccd
            ) {
        List<BookingResponse> items = bookingService.findAllBookingsByCustomerCccd(cccd);
        return ApiResponse.ok("Success!", ListResponse.of(items));
    }
}
