package org.example.hotelbooking.repository;

import org.example.hotelbooking.domain.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomTypeRepository extends JpaRepository<RoomType, String> {
    Optional<RoomType> findByName(String name);
    boolean existsByName(String name);
}
