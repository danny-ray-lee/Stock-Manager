package io.github.danny.ray.stockmanager.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Objects;

public class BigDecimalUtils {

    public static BigDecimal getAverage(Collection<BigDecimal> bigDecimals) {
        return getAverage(bigDecimals, bigDecimals.size());
    }

    public static BigDecimal getAverage(Collection<BigDecimal> bigDecimals, int divideCount) {
        if (bigDecimals == null || bigDecimals.isEmpty()) return BigDecimal.ZERO;

        BigDecimal sum = bigDecimals.stream()
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sum.divide(
                BigDecimal.valueOf(divideCount),
                5,
                RoundingMode.HALF_UP
        );
    }

}
