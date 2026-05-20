package org.example.hotelbooking.repository;

import jakarta.persistence.LockModeType;
import org.example.hotelbooking.domain.BookingStatus;
import org.example.hotelbooking.domain.Room;
import org.example.hotelbooking.domain.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, String> {

    @Query("select r from Room r join fetch r.roomType order by r.roomName asc")
    List<Room> findAllWithRoomType();

    @Query(value = "select r from Room r where r.status = :status")
    List<Room> findAllRoomAvailable(@Param("status")RoomStatus status);
}
