package org.example.hotelbooking.repository;

import org.example.hotelbooking.domain.Booking;
import org.example.hotelbooking.domain.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface    BookingRepository extends JpaRepository<Booking, String> {
    @Query(value = "select b from Booking b join fetch b.room where b.id = :id")
    Optional<Booking> findBookingById(@Param("id") String id);

    @Query(value = "select b from Booking b join fetch b.room where b.customerCccd = :customerCccd")
    List<Booking> findBookingByCustomerCccd(@Param("customerCccd") String customerCccd);



}
