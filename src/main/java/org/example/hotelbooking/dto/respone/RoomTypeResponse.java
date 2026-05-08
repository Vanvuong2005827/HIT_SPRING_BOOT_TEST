package org.example.hotelbooking.dto.respone;

import org.example.hotelbooking.domain.RoomType;

import java.math.BigDecimal;

public record RoomTypeResponse(
        String id,
        String name,
        Integer maxOccupancy,
        BigDecimal basePrice
) {

    public static RoomTypeResponse from(RoomType roomType) {
        return new RoomTypeResponse(
                roomType.getId(),
                roomType.getName(),
                roomType.getMaxOccupancy(),
                roomType.getBasePrice()
        );
    }
}
