package org.example.hotelbooking.common.util;

import java.math.BigDecimal;

public final class PriceCalculator {

    private PriceCalculator() {
    }

    public static BigDecimal calculate(BigDecimal pricePerNight, long nights) {
        // TO DO
        long actualNights;
        if (nights <= 0) {
            actualNights = 1;
        } else {
            actualNights = nights;
        }
        BigDecimal total = pricePerNight.multiply(BigDecimal.valueOf(actualNights));

        return total;

    }
}
