package io.github.danny.ray.stockmanager.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.github.danny.ray.stockmanager.model.enums.EnumTradeType;
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
 * 交易記錄實體類別
 */
@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    private Positions positions;

    @Enumerated(EnumType.STRING)
    @Column(name = "trade_type", nullable = false)
    private EnumTradeType tradeType = EnumTradeType.BUY;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantity;

    @Column(length = 255)
    private String comment;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Transactions() {
    }

    public Transactions(int id, Positions positions, EnumTradeType tradeType, BigDecimal price, int quantity, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.positions = positions;
        this.tradeType = tradeType;
        this.price = price;
        this.quantity = quantity;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public Transactions setId(int id) {
        this.id = id;
        return this;
    }

    public Positions getPositions() {
        return positions;
    }

    public Transactions setPositions(Positions positions) {
        this.positions = positions;
        return this;
    }

    public EnumTradeType getTradeType() {
        return tradeType;
    }

    public Transactions setTradeType(EnumTradeType tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Transactions setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Transactions setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Transactions setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Transactions setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
