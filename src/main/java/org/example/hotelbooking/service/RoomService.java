package org.example.hotelbooking.service;

import org.example.hotelbooking.dto.RoomResponse;
import org.example.hotelbooking.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static org.example.hotelbooking.domain.RoomStatus.AVAILABLE;

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
    public List<RoomResponse> getAvailableRooms(Instant checkInDateTime, Instant checkOutDateTime) {
        return roomRepository.findAvailableRoomsForDateRange(checkInDateTime, checkOutDateTime)
                .stream()
                .map(RoomResponse::from)
                .toList();
    }
}

