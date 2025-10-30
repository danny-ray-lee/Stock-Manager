package io.github.danny.ray.stockmanager.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.github.danny.ray.stockmanager.model.enums.EnumPositionDirection;
import io.github.danny.ray.stockmanager.model.enums.EnumPositionStatus;
import io.github.danny.ray.stockmanager.model.fee.FeePlan;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fee_plan_id", nullable = false)
    private FeePlan feePlan;

    @Enumerated(EnumType.STRING)
    private EnumPositionStatus status = EnumPositionStatus.OPEN;

    @Enumerated(EnumType.STRING)
    private EnumPositionDirection direction = EnumPositionDirection.LONG;

    @Column(nullable = false)
    private int quantity = 1;

    @Column(name = "average_cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal averageCost = BigDecimal.ZERO;

    @Column(name = "balance_cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal balanceCost = BigDecimal.ZERO;

    @Column(name = "total_tax", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalTax = BigDecimal.ZERO;

    @Column(name = "open_at", nullable = false, updatable = false)
    private LocalDateTime openAt = LocalDateTime.now();

    @Column(name = "close_at")
    private LocalDateTime closeAt;

    @Column(name = "comment")
    private String comment = null;

    public Position() {
    }

    public Position(int id, User user, Product product, FeePlan feePlan, EnumPositionStatus status, EnumPositionDirection direction, int quantity, BigDecimal averageCost, BigDecimal balanceCost, BigDecimal totalTax, LocalDateTime openAt, LocalDateTime closeAt, String comment) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.feePlan = feePlan;
        this.status = status;
        this.direction = direction;
        this.quantity = quantity;
        this.averageCost = averageCost;
        this.balanceCost = balanceCost;
        this.totalTax = totalTax;
        this.openAt = openAt;
        this.closeAt = closeAt;
        this.comment = comment;
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

    public FeePlan getFeePlan() {
        return feePlan;
    }

    public Position setFeePlan(FeePlan feePlan) {
        this.feePlan = feePlan;
        return this;
    }

    public EnumPositionStatus getStatus() {
        return status;
    }

    public Position setStatus(EnumPositionStatus status) {
        this.status = status;
        return this;
    }

    public EnumPositionDirection getDirection() {
        return direction;
    }

    public Position setDirection(EnumPositionDirection direction) {
        this.direction = direction;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Position setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getAverageCost() {
        return averageCost;
    }

    public Position setAverageCost(BigDecimal averageCost) {
        this.averageCost = averageCost;
        return this;
    }

    public BigDecimal getBalanceCost() {
        return balanceCost;
    }

    public Position setBalanceCost(BigDecimal balanceCost) {
        this.balanceCost = balanceCost;
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

    public String getComment() {
        return comment;
    }

    public Position setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
