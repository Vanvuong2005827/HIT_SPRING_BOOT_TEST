package org.example.hotelbooking.common.util;

import java.math.BigDecimal;

public final class PriceCalculator {

    private PriceCalculator() {
    }

    public static BigDecimal calculate(BigDecimal pricePerNight, long nights) {
        // TO DO
        if(nights<0){
            throw new IllegalArgumentException("Số đêm không thể âm");
        }
        if (pricePerNight == null) {
            throw new IllegalArgumentException("Giá tiền mỗi đêm không thể null");
        }
        return pricePerNight.multiply(BigDecimal.valueOf(nights));
    }
}
