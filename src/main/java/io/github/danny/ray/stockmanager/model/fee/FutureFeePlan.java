package io.github.danny.ray.stockmanager.model.fee;

import java.math.BigDecimal;

import io.github.danny.ray.stockmanager.model.enums.EnumProductType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("FUTURE")
public class FutureFeePlan extends FeePlan {

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private final EnumProductType type = EnumProductType.FUTURE;


    @Column(name = "fixed_fee", precision = 10, scale = 2)
    private BigDecimal fixedFee = BigDecimal.ZERO;

    public BigDecimal getFixedFee() {
        return fixedFee;
    }

    public FutureFeePlan setFixedFee(BigDecimal fixedFee) {
        this.fixedFee = fixedFee;
        return this;
    }

    @Override
    public EnumProductType getType() {
        return EnumProductType.FUTURE;
    }
}
