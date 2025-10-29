package io.github.danny.ray.stockmanager.model.fee;

import java.math.BigDecimal;

import io.github.danny.ray.stockmanager.model.enums.EnumProductType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("STOCK")
public class StockFeePlan extends FeePlan {

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private final EnumProductType type = EnumProductType.STOCK;

    @Column(name = "fee_rate", precision = 10, scale = 2)
    private BigDecimal feeRate = BigDecimal.ZERO;

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public StockFeePlan setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
        return this;
    }

    @Override
    public EnumProductType getType() {
        return EnumProductType.STOCK;
    }

}
