package org.example.hotelbooking.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.example.hotelbooking.common.response.ApiResponse;
import org.example.hotelbooking.constant.ApiPath;
import org.example.hotelbooking.constant.SuccessMessage;
import org.example.hotelbooking.dto.BookingResponse;
import org.example.hotelbooking.dto.CreateBookingRequest;
import org.example.hotelbooking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    public ApiResponse<BookingResponse> getBooking(@RequestParam("booking_id") String bookingId
    ) {
        BookingResponse hihi= bookingService.getBookingById(bookingId);
        return ApiResponse.ok(SuccessMessage.FETCH_DATA_SUCCESS,hihi) ;
    }

    @GetMapping("/bookings")
    public ApiResponse<List<BookingResponse>> getBookingsByCustomer(
            @RequestParam("cccd")
            @Pattern(regexp = "^\\d{12}$", message = "Số CCCD không hợp lệ, phải bao gồm đúng 12 chữ số")
            String cccd
    ) {
        List<BookingResponse> data = bookingService.findBookingByCccd(cccd);
        return ApiResponse.ok(SuccessMessage.FETCH_DATA_SUCCESS, data);
    }


    @PostMapping("/booking")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<BookingResponse> createBooking(@Valid @RequestBody CreateBookingRequest request) {
        BookingResponse data=bookingService.createBooking(request);
        return ApiResponse.ok(SuccessMessage.CREATE_BOOKING_SUCCESS, data);
    }

    @PatchMapping("/booking/{booking_id}/cancel")
    public ApiResponse<BookingResponse> cancelBooking(@PathVariable("booking_id") String bookingId
    ) {
        BookingResponse data= bookingService.cancelBooking(bookingId);
        return ApiResponse.ok(SuccessMessage.CANCEL_BOOKING_SUCCESS, data);
    }
}
