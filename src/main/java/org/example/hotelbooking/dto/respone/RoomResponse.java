package org.example.hotelbooking.dto.respone;

import org.example.hotelbooking.domain.Room;
import org.example.hotelbooking.domain.RoomStatus;

import java.time.Instant;

public record RoomResponse(
        String id,
        String roomName,
        RoomStatus status,
        RoomTypeResponse roomType,
        Instant createdAt,
        Instant updatedAt
) {

    public static RoomResponse from(Room room) {
        return new RoomResponse(
                room.getId(),
                room.getRoomName(),
                room.getStatus(),
                RoomTypeResponse.from(room.getRoomType()),
                room.getCreatedAt(),
                room.getUpdatedAt()
        );
    }
}
