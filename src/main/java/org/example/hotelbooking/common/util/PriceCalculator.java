package org.example.hotelbooking.common.util;

import java.math.BigDecimal;

public final class PriceCalculator {

    private PriceCalculator() {
    }

    public static BigDecimal calculate(BigDecimal pricePerNight, long nights) {
        return pricePerNight.multiply(BigDecimal.valueOf(nights));
    }

//    public static Double calculate(Double pricePerNight, long nights) {
//        return pricePerNight * nights;
//    }
}
