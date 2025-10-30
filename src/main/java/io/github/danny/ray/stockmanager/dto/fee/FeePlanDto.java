package io.github.danny.ray.stockmanager.dto.fee;

import java.math.BigDecimal;

import io.github.danny.ray.stockmanager.model.enums.EnumCurrencySymbol;
import io.github.danny.ray.stockmanager.model.enums.EnumProductType;

public class FeePlanDto {

    private int id = 0;
    private String name;
    private EnumProductType type;
    private EnumCurrencySymbol currency;
    private BigDecimal feeRate = BigDecimal.ZERO;
    private BigDecimal fixedFee = BigDecimal.ZERO;

    public int getId() {
        return id;
    }

    public FeePlanDto setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FeePlanDto setName(String name) {
        this.name = name;
        return this;
    }

    public EnumProductType getType() {
        return type;
    }

    public FeePlanDto setType(EnumProductType type) {
        this.type = type;
        return this;
    }

    public EnumCurrencySymbol getCurrency() {
        return currency;
    }

    public FeePlanDto setCurrency(EnumCurrencySymbol currency) {
        this.currency = currency;
        return this;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public FeePlanDto setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
        return this;
    }

    public BigDecimal getFixedFee() {
        return fixedFee;
    }

    public FeePlanDto setFixedFee(BigDecimal fixedFee) {
        this.fixedFee = fixedFee;
        return this;
    }
}
