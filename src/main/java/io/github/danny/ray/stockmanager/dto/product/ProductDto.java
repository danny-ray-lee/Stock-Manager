package io.github.danny.ray.stockmanager.dto.product;

import java.math.BigDecimal;

import io.github.danny.ray.stockmanager.model.enums.EnumCurrencySymbol;
import io.github.danny.ray.stockmanager.model.enums.EnumProductType;

public class ProductDto {

    private int id = 0;

    private String name;

    private String symbol;

    private EnumCurrencySymbol currency;

    private BigDecimal stepPoint;

    private BigDecimal stepPrice;

    private EnumProductType type;

    public ProductDto() {
    }

    public ProductDto(int id, String name, String symbol, EnumCurrencySymbol currency, BigDecimal stepPoint, BigDecimal stepPrice, EnumProductType type) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.currency = currency;
        this.stepPoint = stepPoint;
        this.stepPrice = stepPrice;
        this.type = type;
    }

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

    public BigDecimal getStepPoint() {
        return stepPoint;
    }

    public ProductDto setStepPoint(BigDecimal stepPoint) {
        this.stepPoint = stepPoint;
        return this;
    }

    public BigDecimal getStepPrice() {
        return stepPrice;
    }

    public ProductDto setStepPrice(BigDecimal stepPrice) {
        this.stepPrice = stepPrice;
        return this;
    }

    public EnumProductType getType() {
        return type;
    }

    public ProductDto setType(EnumProductType type) {
        this.type = type;
        return this;
    }
}
