package org.example.hotelbooking.service;

import org.example.hotelbooking.constant.ErrorMessage;
import org.example.hotelbooking.domain.BookingStatus;
import org.example.hotelbooking.domain.Room;
import org.example.hotelbooking.domain.RoomStatus;
import org.example.hotelbooking.dto.RoomResponse;
import org.example.hotelbooking.exception.BadRequestException;
import org.example.hotelbooking.exception.ResourceNotFoundException;
import org.example.hotelbooking.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
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
    public List<RoomResponse> findByStatus(RoomStatus status){
        List<Room> rooms = roomRepository.findByStatus(status);
        List<RoomResponse> roomResponseList = new ArrayList<>();

        if (rooms.size() == 0) throw new ResourceNotFoundException("Status", status.toString());

        for (Room r : rooms){
            roomResponseList.add(RoomResponse.from(r));
        }
        return roomResponseList;
    }

}
