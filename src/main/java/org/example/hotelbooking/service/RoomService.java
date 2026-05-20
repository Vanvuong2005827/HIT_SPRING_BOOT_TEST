package org.example.hotelbooking.service;

import org.example.hotelbooking.constant.ErrorMessage;
import org.example.hotelbooking.domain.BookingStatus;
import org.example.hotelbooking.domain.RoomStatus;
import org.example.hotelbooking.dto.RoomResponse;
import org.example.hotelbooking.exception.BadRequestException;
import org.example.hotelbooking.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Transactional(readOnly = true)
    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAllWithRoomType()
                .stream()
                .map(RoomResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<RoomResponse> getAllRoomsAvailable(){
        return roomRepository.findAllRoomAvailable(RoomStatus.AVAILABLE)
                .stream().map(RoomResponse::from)
                .toList();
    }
}
