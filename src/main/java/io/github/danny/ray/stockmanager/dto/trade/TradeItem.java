package io.github.danny.ray.stockmanager.dto.trade;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * for flat map all transactions
 */
public record TradeItem(
        BigDecimal price,
        LocalDateTime tradeTime
) {
}
