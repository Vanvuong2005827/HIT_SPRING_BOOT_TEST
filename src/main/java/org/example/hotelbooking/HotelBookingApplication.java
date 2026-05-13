package org.example.hotelbooking;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelBookingApplication.class, args);
    }
//    @PostConstruct
//    public void check() {
//        System.out.println("DB_URL = " + System.getenv("DB_URL"));
//    }
}
