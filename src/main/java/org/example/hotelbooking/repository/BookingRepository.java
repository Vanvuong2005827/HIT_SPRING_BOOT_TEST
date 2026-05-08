package org.example.hotelbooking.repository;

import org.example.hotelbooking.domain.Booking;
import org.example.hotelbooking.domain.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, String> {

    @Query(value = "select b from Booking b where b.id = :id")
    List<Booking> findBookingsById(@Param("id")String id);
    @Query(value = "select b from Booking b where b.customerCccd = :customerCccd")
    List<Booking> findBookingsByCustomerCccd(@Param("customerCccd") String customerCccd);
}
