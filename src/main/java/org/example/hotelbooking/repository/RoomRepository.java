package org.example.hotelbooking.repository;

import org.example.hotelbooking.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

    @Query("select r from Room r join fetch r.roomType order by r.roomName asc")
    List<Room> findAllWithRoomType();

    @Query("select r from Room r join fetch r.roomType where r.status = org.example.hotelbooking.domain.RoomStatus.AVAILABLE order by r.roomName")
    List<Room> findAllWithAvailable();
}
