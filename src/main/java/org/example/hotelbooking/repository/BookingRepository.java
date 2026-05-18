package org.example.hotelbooking.repository;

import org.example.hotelbooking.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, String> {

    List<Booking> findByCustomerCccd(String customerCccd);

    @Query("""
        SELECT COUNT(b) FROM Booking b 
        WHERE b.room.id = :roomId 
          AND b.status <> 'CANCELLED' 
          AND b.checkInDateTime < :checkOut 
          AND b.checkOutDateTime > :checkIn
    """)
    long countOverlappingBookings(
            @Param("roomId") String roomId,
            @Param("checkIn") Instant checkIn,
            @Param("checkOut") Instant checkOut
    );
}