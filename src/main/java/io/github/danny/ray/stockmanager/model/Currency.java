package io.github.danny.ray.stockmanager.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

/**
 * 貨幣實體類別
 */
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @Column(length = 10)
    private String symbol;
 
    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "last_update_at")
    private LocalDateTime lastUpdateAt = LocalDateTime.now();

    public Currency() {
    }

    public Currency(String symbol, String name, BigDecimal price, LocalDateTime lastUpdateAt) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.lastUpdateAt = lastUpdateAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateAt = LocalDateTime.now();
    }

    public String getSymbol() {
        return symbol;
    }

    public Currency setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getName() {
        return name;
    }

    public Currency setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Currency setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getLastUpdateAt() {
        return lastUpdateAt;
    }

    public Currency setLastUpdateAt(LocalDateTime lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
        return this;
    }
}
