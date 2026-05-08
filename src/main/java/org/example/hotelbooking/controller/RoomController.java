package org.example.hotelbooking.controller;

import org.example.hotelbooking.common.response.ApiResponse;
import org.example.hotelbooking.common.response.ListResponse;
import org.example.hotelbooking.constant.ApiPath;
import org.example.hotelbooking.dto.respone.RoomResponse;
import org.example.hotelbooking.service.RoomService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ApiResponse<ListResponse<RoomResponse>> getRooms() {
        List<RoomResponse> items = roomService.getAllRooms();
        return ApiResponse.ok("Success!", ListResponse.of(items));
    }

    @GetMapping("/rooms/available")
    public ApiResponse<ListResponse<RoomResponse>> getAvailableRooms(
    ) {
        List<RoomResponse> items = roomService.getAllAvailableRoom();
        return ApiResponse.ok("Success!", ListResponse.of(items));
    }
}
