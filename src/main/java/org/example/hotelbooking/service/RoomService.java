package org.example.hotelbooking.service;

import org.example.hotelbooking.domain.RoomStatus;
import org.example.hotelbooking.dto.respone.RoomResponse;
import org.example.hotelbooking.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<RoomResponse> getAllAvailableRoom() {
        return roomRepository.findAllByStatusWithRoomType(RoomStatus.AVAILABLE)
                .stream()
                .map(RoomResponse::from)
                .toList();
    }
}
