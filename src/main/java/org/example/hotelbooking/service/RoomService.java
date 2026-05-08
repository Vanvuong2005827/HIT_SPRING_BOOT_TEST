package org.example.hotelbooking.service;

import org.example.hotelbooking.constant.ErrorMessage;
import org.example.hotelbooking.dto.AvailableRoomsRequest;
import org.example.hotelbooking.dto.RoomResponse;
import org.example.hotelbooking.exception.BadRequestException;
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

    @Transactional(readOnly = true)
    public List<RoomResponse> getAvailableRooms(AvailableRoomsRequest request) {
        if (request.checkInDateTime() != null && request.checkOutDateTime() != null &&
                !request.checkInDateTime().isBefore(request.checkOutDateTime())) {
            throw new BadRequestException(ErrorMessage.INVALID_DATES);
        }

        return roomRepository.findAvailableRooms(request.checkInDateTime(), request.checkOutDateTime())
                .stream()
                .map(RoomResponse::from)
                .toList();
    }
}