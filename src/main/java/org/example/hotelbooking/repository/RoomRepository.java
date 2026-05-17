package org.example.hotelbooking.repository;

import org.example.hotelbooking.domain.Room;
import org.example.hotelbooking.domain.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

    @Query("select r from Room r join fetch r.roomType order by r.roomName asc")
    List<Room> findAllWithRoomType();

    @Query("select r from Room r join fetch r.roomType where r.status = :status order by r.roomName asc")
    List<Room> findAllByStatusWithRoomType(@Param("status") RoomStatus status);
    @Query("""
        SELECT r FROM Room r JOIN FETCH r.roomType 
        WHERE r.status = 'AVAILABLE' 
        AND NOT EXISTS (
            SELECT b FROM Booking b 
            WHERE b.room = r 
            AND b.status != 'CANCELLED' 
            AND b.checkInDateTime < :checkOut 
            AND b.checkOutDateTime > :checkIn
        )
        ORDER BY r.roomName ASC
    """)
    List<Room> findAvailableRoomsForDateRange(
            @Param("checkIn") Instant checkIn,
            @Param("checkOut") Instant checkOut
    );

}
