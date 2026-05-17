package org.example.hotelbooking.common.util;

import java.math.BigDecimal;

public final class PriceCalculator {
    private PriceCalculator() {}

    public static BigDecimal calculate(BigDecimal pricePerNight, long nights) {
        if (pricePerNight == null || nights <= 0) {
            return BigDecimal.ZERO;
        }
        return pricePerNight.multiply(BigDecimal.valueOf(nights));
    }
}