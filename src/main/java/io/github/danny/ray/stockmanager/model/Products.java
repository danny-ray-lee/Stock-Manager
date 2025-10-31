package io.github.danny.ray.stockmanager.model;

import java.math.BigDecimal;

import io.github.danny.ray.stockmanager.model.enums.EnumCurrencySymbol;
import io.github.danny.ray.stockmanager.model.enums.EnumProductType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 商品實體類別
 */
@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 20, unique = true)
    private String symbol;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private EnumCurrencySymbol currency;

    @Column(name = "step_point", nullable = false)
    private BigDecimal stepPoint = BigDecimal.ONE;

    @Column(name = "step_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal stepPrice = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumProductType type = EnumProductType.STOCK;

    public Products() {
    }

    public Products(int id, String name, String symbol, EnumCurrencySymbol currency, BigDecimal stepPoint, BigDecimal stepPrice, EnumProductType type) {
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

    public Products setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Products setName(String name) {
        this.name = name;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public Products setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public EnumCurrencySymbol getCurrency() {
        return currency;
    }

    public Products setCurrency(EnumCurrencySymbol currency) {
        this.currency = currency;
        return this;
    }

    public BigDecimal getStepPoint() {
        return stepPoint;
    }

    public Products setStepPoint(BigDecimal stepPoint) {
        this.stepPoint = stepPoint;
        return this;
    }

    public BigDecimal getStepPrice() {
        return stepPrice;
    }

    public Products setStepPrice(BigDecimal stepPrice) {
        this.stepPrice = stepPrice;
        return this;
    }

    public EnumProductType getType() {
        return type;
    }

    public Products setType(EnumProductType type) {
        this.type = type;
        return this;
    }
}
