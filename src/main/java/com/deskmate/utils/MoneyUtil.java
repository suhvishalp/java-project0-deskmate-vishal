package com.deskmate.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class MoneyUtil {
	
    private MoneyUtil() {}

    public static BigDecimal parse(String input) {
        try {
            return new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid money value: " + input);
        }
    }
}

