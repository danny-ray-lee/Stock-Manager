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
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * 持倉實體類別
 */
@Entity
@Table(name = "positions",
        indexes = {
                @Index(name = "idx_user", columnList = "user_id"),
                @Index(name = "idx_user_product", columnList = "user_id, product_id")
        })
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('OPEN', 'CLOSE') DEFAULT 'OPEN'")
    private PositionStatus status = PositionStatus.OPEN;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('LONG', 'SHORT') DEFAULT 'LONG'")
    private PositionDirection direction = PositionDirection.LONG;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 1")
    private int quantity = 1;

    @Column(name = "average_price", nullable = false, precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private BigDecimal averagePrice = BigDecimal.ZERO;

    @Column(name = "total_tax", nullable = false, precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private BigDecimal totalTax = BigDecimal.ZERO;

    @Column(name = "open_at", nullable = false, updatable = false)
    private LocalDateTime openAt = LocalDateTime.now();

    @Column(name = "close_at")
    private LocalDateTime closeAt;

    public Position() {
    }

    public Position(int id, User user, Product product, PositionStatus status, PositionDirection direction, int quantity, BigDecimal averagePrice, BigDecimal totalTax, LocalDateTime openAt, LocalDateTime closeAt) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.status = status;
        this.direction = direction;
        this.quantity = quantity;
        this.averagePrice = averagePrice;
        this.totalTax = totalTax;
        this.openAt = openAt;
        this.closeAt = closeAt;
    }

    public int getId() {
        return id;
    }

    public Position setId(int id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Position setUser(User user) {
        this.user = user;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public Position setProduct(Product product) {
        this.product = product;
        return this;
    }

    public PositionStatus getStatus() {
        return status;
    }

    public Position setStatus(PositionStatus status) {
        this.status = status;
        return this;
    }

    public PositionDirection getDirection() {
        return direction;
    }

    public Position setDirection(PositionDirection direction) {
        this.direction = direction;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Position setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public Position setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
        return this;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public Position setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
        return this;
    }

    public LocalDateTime getOpenAt() {
        return openAt;
    }

    public Position setOpenAt(LocalDateTime openAt) {
        this.openAt = openAt;
        return this;
    }

    public LocalDateTime getCloseAt() {
        return closeAt;
    }

    public Position setCloseAt(LocalDateTime closeAt) {
        this.closeAt = closeAt;
        return this;
    }

    public enum PositionStatus {
        OPEN, CLOSE
    }

    public enum PositionDirection {
        LONG, SHORT
    }
}
