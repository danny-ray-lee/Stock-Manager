package io.github.danny.ray.stockmanager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 貨幣實體類別
 */
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrencySymbol symbol;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cashBuy;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cashSell;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal spotBuy;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal spotSell;

    @Column(name = "last_update_at")
    private LocalDateTime lastUpdateAt = null;

    public Currency() {
    }

    public Currency(CurrencySymbol symbol) {
        this.symbol = symbol;
        this.name = symbol.getDesc();
    }

    public Currency(int id, CurrencySymbol symbol, String name, BigDecimal cashBuy, BigDecimal cashSell, BigDecimal spotBuy, BigDecimal spotSell, LocalDateTime lastUpdateAt) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.cashBuy = cashBuy;
        this.cashSell = cashSell;
        this.spotBuy = spotBuy;
        this.spotSell = spotSell;
        this.lastUpdateAt = lastUpdateAt;
    }

    @PreUpdate
    @PrePersist
    protected void onUpdate() {
        this.lastUpdateAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public Currency setId(int id) {
        this.id = id;
        return this;
    }

    public CurrencySymbol getSymbol() {
        return symbol;
    }

    public Currency setSymbol(CurrencySymbol symbol) {
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

    public BigDecimal getCashBuy() {
        return cashBuy;
    }

    public Currency setCashBuy(BigDecimal cashBuy) {
        this.cashBuy = cashBuy;
        return this;
    }

    public BigDecimal getCashSell() {
        return cashSell;
    }

    public Currency setCashSell(BigDecimal cashSell) {
        this.cashSell = cashSell;
        return this;
    }

    public BigDecimal getSpotBuy() {
        return spotBuy;
    }

    public Currency setSpotBuy(BigDecimal spotBuy) {
        this.spotBuy = spotBuy;
        return this;
    }

    public BigDecimal getSpotSell() {
        return spotSell;
    }

    public Currency setSpotSell(BigDecimal spotSell) {
        this.spotSell = spotSell;
        return this;
    }

    public LocalDateTime getLastUpdateAt() {
        return lastUpdateAt;
    }

    public Currency setLastUpdateAt(LocalDateTime lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
        return this;
    }

    public enum CurrencySymbol {
        TWD("新台幣"),
        USD("美金"),
        JPY("日圓");

        private String desc;

        CurrencySymbol(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
