package org.example.hotelbooking.controller;

import jakarta.validation.Valid;
import org.example.hotelbooking.common.response.ApiResponse;
import org.example.hotelbooking.common.response.ListResponse;
import org.example.hotelbooking.constant.ApiPath;
import org.example.hotelbooking.constant.SuccessMessage;
import org.example.hotelbooking.dto.AvailableRoomsRequest;
import org.example.hotelbooking.dto.RoomResponse;
import org.example.hotelbooking.service.RoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@Validated
@RestController
@RequestMapping(ApiPath.API_V1)
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public ResponseEntity<ApiResponse<ListResponse<RoomResponse>>> getRooms() {
        List<RoomResponse> items = roomService.getAllRooms();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.ok("Success!", ListResponse.of(items)));
    }

    @GetMapping("/rooms/available")
<<<<<<< HEAD
    public ApiResponse<ListResponse<RoomResponse>> getAvailableRooms(
    ) {
=======
    public ResponseEntity<ApiResponse<ListResponse<RoomResponse>>> getAvailableRooms(
    ) {
        List<RoomResponse> roomResponseList = roomService.findRoomByStatus(RoomStatus.AVAILABLE);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.ok("Thành công", ListResponse.of(roomResponseList)));
>>>>>>> e18bc9b (fix: repair my project)
    }
}
