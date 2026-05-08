package org.example.hotelbooking.repository;

import org.example.hotelbooking.domain.Booking;
import org.example.hotelbooking.domain.BookingStatus;
import org.example.hotelbooking.dto.respone.BookingResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    Booking getBookingById(String id);

    List<Booking> getBookingByCustomerCccd(String customerCccd);

    // TO DO
}
