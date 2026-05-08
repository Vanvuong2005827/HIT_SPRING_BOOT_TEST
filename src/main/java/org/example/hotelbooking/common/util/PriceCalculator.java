package org.example.hotelbooking.common.util;

import java.math.BigDecimal;

public final class PriceCalculator {

    private PriceCalculator() {
    }

    public static BigDecimal calculate(BigDecimal pricePerNight, long nights) {
        // TO DO
        return BigDecimal.valueOf(nights * pricePerNight.doubleValue()) ;
    }
}
