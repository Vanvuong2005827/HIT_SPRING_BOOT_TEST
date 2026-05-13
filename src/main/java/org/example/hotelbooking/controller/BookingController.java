package org.example.hotelbooking.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.hotelbooking.common.response.ApiResponse;
import org.example.hotelbooking.common.response.ListResponse;
import org.example.hotelbooking.constant.ApiPath;
import org.example.hotelbooking.constant.ErrorMessage;
import org.example.hotelbooking.constant.SuccessMessage;
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
<<<<<<< HEAD
    public ApiResponse<BookingResponse> getBooking(
    ) {
    }

    @GetMapping("/bookings")
    public ApiResponse<ListResponse<BookingResponse>> getBookingsByCustomer(
    ) {
=======
    public ResponseEntity<ApiResponse<BookingResponse>> getBookingById(
            @RequestParam String id
    ) {
        BookingResponse bookingResponses = bookingService.findBookingById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.ok("Lấy booking thành công",bookingResponses));
    }

    @GetMapping("/bookings")
    public ResponseEntity<ApiResponse<ListResponse<BookingResponse>>> getBookingsByCustomer(
            @RequestParam String customerCccd
    ) {
        List<BookingResponse> bookingResponses = bookingService.findBookingByCustomerCccd(customerCccd);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.ok("Lấy danh sách booking thành công", ListResponse.of(bookingResponses)));
>>>>>>> e18bc9b (fix: repair my project)
    }

    @PostMapping("/booking")
    @ResponseStatus(HttpStatus.CREATED)
<<<<<<< HEAD
    public ApiResponse<BookingResponse> createBooking() {

    }

    @PatchMapping("/booking/{booking_id}/cancel")
    public ApiResponse<BookingResponse> cancelBooking(
    ) {
=======
    public ResponseEntity<ApiResponse<BookingResponse>> createBooking(@RequestBody CreateBookingRequest createBookingRequest) {
        BookingResponse bookingResponse = bookingService.createBooking(createBookingRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Thành công", bookingResponse));
    }

    @PatchMapping("/booking/{booking_id}/cancel")
    public ResponseEntity<ApiResponse<BookingResponse>> cancelBooking(
            @PathVariable String id
    ) {
        if (bookingService.cancelBooking(id)){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponse.ok("Thành công", null));
        }
        else return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Thật bại"));
>>>>>>> e18bc9b (fix: repair my project)
    }
}
