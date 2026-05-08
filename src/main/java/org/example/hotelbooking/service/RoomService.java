package org.example.hotelbooking.service;

import org.example.hotelbooking.constant.ErrorMessage;
import org.example.hotelbooking.domain.BookingStatus;
import org.example.hotelbooking.domain.Room;
import org.example.hotelbooking.domain.RoomStatus;
import org.example.hotelbooking.domain.RoomType;
import org.example.hotelbooking.dto.RoomResponse;
import org.example.hotelbooking.exception.BadRequestException;
import org.example.hotelbooking.repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
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
    public List<RoomResponse> getAllroomByStatus(RoomStatus roomStatus) {
        List<RoomResponse> roomResponseList = new ArrayList<>();
        for (Room room : roomRepository.findAll()) {
            if (room.getRoomType().equals(AVAILABLE)) {
                roomResponseList.add(RoomResponse.from(room));
            }
        }
            return roomResponseList;
    }







}
