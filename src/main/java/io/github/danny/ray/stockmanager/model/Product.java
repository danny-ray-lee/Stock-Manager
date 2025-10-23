package io.github.danny.ray.stockmanager.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * 商品實體類別
 */
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 20, unique = true)
    private String symbol;

    @Column(name = "currency", nullable = false)
    private String currencySymbol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency", referencedColumnName = "symbol", nullable = false, insertable = false, updatable = false)
    private Currency currency = null;

    @Column(name = "step_point", nullable = false, columnDefinition = "INT DEFAULT 1")
    private int stepPoint = 1;

    @Column(name = "future_fee", precision = 10, scale = 2)
    private BigDecimal futureFee;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('STOCK', 'FUTURE') DEFAULT 'STOCK'")
    private ProductType type = ProductType.STOCK;

    public Product() {
    }

    public Product(int id, String name, String symbol, String currencySymbol, int stepPoint, BigDecimal futureFee, ProductType type) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.currencySymbol = currencySymbol;
        this.stepPoint = stepPoint;
        this.futureFee = futureFee;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public Product setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public Product setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public Product setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Product setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public int getStepPoint() {
        return stepPoint;
    }

    public Product setStepPoint(int stepPoint) {
        this.stepPoint = stepPoint;
        return this;
    }

    public BigDecimal getFutureFee() {
        return futureFee;
    }

    public Product setFutureFee(BigDecimal futureFee) {
        this.futureFee = futureFee;
        return this;
    }

    public ProductType getType() {
        return type;
    }

    public Product setType(ProductType type) {
        this.type = type;
        return this;
    }

    public enum ProductType {
        STOCK, FUTURE
    }
}
