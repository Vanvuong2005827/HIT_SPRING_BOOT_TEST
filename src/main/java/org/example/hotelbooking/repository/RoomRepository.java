package org.example.hotelbooking.repository;

import org.example.hotelbooking.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, String> {

    @Query("select r from Room r join fetch r.roomType order by r.roomName asc")
    List<Room> findAllWithRoomType();

    @Query("""
        SELECT r FROM Room r JOIN FETCH r.roomType 
        WHERE r.status = 'AVAILABLE' 
        AND r.id NOT IN (
            SELECT b.room.id FROM Booking b 
            WHERE b.status <> 'CANCELLED' 
            AND b.checkInDateTime < :checkOut 
            AND b.checkOutDateTime > :checkIn
        )
        ORDER BY r.roomName ASC
    """)
    List<Room> findAvailableRooms(
            @Param("checkIn") Instant checkIn,
            @Param("checkOut") Instant checkOut
    );
}