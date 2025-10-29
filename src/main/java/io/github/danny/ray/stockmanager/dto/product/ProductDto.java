package io.github.danny.ray.stockmanager.dto.product;

import java.math.BigDecimal;

import io.github.danny.ray.stockmanager.model.Product.ProductType;
import io.github.danny.ray.stockmanager.model.enums.EnumCurrencySymbol;

public class ProductDto {

    private int id = 0;

    private String name;

    private String symbol;

    private EnumCurrencySymbol currency;

    private int stepPoint = 1;

    private BigDecimal future_fee = BigDecimal.ZERO;

    private ProductType type;

    public int getId() {
        return id;
    }

    public ProductDto setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public ProductDto setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public EnumCurrencySymbol getCurrency() {
        return currency;
    }

    public ProductDto setCurrency(EnumCurrencySymbol currency) {
        this.currency = currency;
        return this;
    }

    public int getStepPoint() {
        return stepPoint;
    }

    public ProductDto setStepPoint(int stepPoint) {
        this.stepPoint = stepPoint;
        return this;
    }

    public BigDecimal getFuture_fee() {
        return future_fee;
    }

    public ProductDto setFuture_fee(BigDecimal future_fee) {
        this.future_fee = future_fee;
        return this;
    }

    public ProductType getType() {
        return type;
    }

    public ProductDto setType(ProductType type) {
        this.type = type;
        return this;
    }
}
