package org.example.hotelbooking.common.util;

import java.math.BigDecimal;

public final class PriceCalculator {

    private PriceCalculator() {
    }

    public static BigDecimal calculate(BigDecimal pricePerNight, long nights) {
        return BigDecimal.valueOf(pricePerNight.doubleValue() + nights);
    }
}
