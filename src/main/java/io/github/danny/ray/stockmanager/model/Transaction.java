package io.github.danny.ray.stockmanager.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @Column(name = "position_id", nullable = false)
    private int positionId = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false, insertable = false, updatable = false)
    private Position position = null;

    @Enumerated(EnumType.STRING)
    @Column(name = "trade_type", nullable = false, columnDefinition = "ENUM('BUY', 'SELL') DEFAULT 'BUY'")
    private TradeType tradeType = TradeType.BUY;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private BigDecimal fee = BigDecimal.ZERO;

    @Column(nullable = false, precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private BigDecimal tax = BigDecimal.ZERO;

    @Column(nullable = false)
    private int quantity;

    @Column(length = 255)
    private String comment;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Transaction() {
    }

    public Transaction(int id, int positionId, TradeType tradeType, BigDecimal price, BigDecimal fee, BigDecimal tax, int quantity, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.positionId = positionId;
        this.tradeType = tradeType;
        this.price = price;
        this.fee = fee;
        this.tax = tax;
        this.quantity = quantity;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public Transaction setId(int id) {
        this.id = id;
        return this;
    }

    public int getPositionId() {
        return positionId;
    }

    public Transaction setPositionId(int positionId) {
        this.positionId = positionId;
        return this;
    }

    public Position getPosition() {
        return position;
    }

    public Transaction setPosition(Position position) {
        this.position = position;
        return this;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public Transaction setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Transaction setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public Transaction setFee(BigDecimal fee) {
        this.fee = fee;
        return this;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public Transaction setTax(BigDecimal tax) {
        this.tax = tax;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Transaction setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Transaction setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Transaction setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public enum TradeType {
        BUY, SELL
    }
}
