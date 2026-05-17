package org.example.hotelbooking.repository;

import org.example.hotelbooking.domain.Booking;
import org.example.hotelbooking.domain.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    @Query("SELECT b from Booking b where b.customerCccd = :customerCccd")
    List<Booking> findBookingByCccd(@Param("customerCccd") String customerCccd);
    // TO DO

}
