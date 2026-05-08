package org.example.hotelbooking.repository;

import org.example.hotelbooking.domain.Booking;
import org.example.hotelbooking.domain.BookingStatus;
import org.example.hotelbooking.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, String> {
    @Query(value = "select b from Booking b where b.id = :id")
    Booking findBookingById(@Param("id") String id);

    @Query(value = "select b from Booking b where b.customerCccd = :cccd")
    Booking findBookingByCCCD(@Param("cccd") String cccd);



}
