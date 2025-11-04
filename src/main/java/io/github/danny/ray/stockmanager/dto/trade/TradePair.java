package io.github.danny.ray.stockmanager.dto.trade;

import java.math.BigDecimal;

import io.github.danny.ray.stockmanager.model.enums.EnumPositionDirection;

/**
 * for map every trade item
 *
 * @param buy
 * @param sell
 */
public record TradePair(
        TradeItem buy,
        TradeItem sell
) {
    public boolean hasNoPair() {
        return buy == null || sell == null;
    }

    public BigDecimal getNoPairPrice() {
        if (buy != null) {
            return buy.price();
        } else if (sell != null) {
            return sell.price();
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getBalance(EnumPositionDirection direction) {
        if (hasNoPair()) {
            return getNoPairPrice();
        }

        return switch (direction) {
            case LONG -> buy.price().subtract(sell.price());
            case SHORT -> sell.price().subtract(buy.price());
        };
    }
}
