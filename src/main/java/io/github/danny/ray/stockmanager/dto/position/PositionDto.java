package io.github.danny.ray.stockmanager.dto.position;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.github.danny.ray.stockmanager.model.enums.EnumPositionDirection;
import io.github.danny.ray.stockmanager.model.enums.EnumPositionStatus;

public class PositionDto {

    private Integer id;

    private Integer productId;

    private Integer feePlanId;

    private EnumPositionStatus status;

    private EnumPositionDirection direction;

    private int quantity;

    private BigDecimal averageCost;

    private BigDecimal balanceCost;

    private BigDecimal totalTax;

    private LocalDateTime openAt;

    private LocalDateTime closeAt;

    private String comment;

    public PositionDto() {
    }

    public PositionDto(Integer id, Integer productId, Integer feePlanId, EnumPositionStatus status, EnumPositionDirection direction, int quantity, BigDecimal averageCost, BigDecimal balanceCost, BigDecimal totalTax, LocalDateTime openAt, LocalDateTime closeAt, String comment) {
        this.id = id;
        this.productId = productId;
        this.feePlanId = feePlanId;
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

    public Integer getId() {
        return id;
    }

    public PositionDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getProductId() {
        return productId;
    }

    public PositionDto setProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public Integer getFeePlanId() {
        return feePlanId;
    }

    public PositionDto setFeePlanId(Integer feePlanId) {
        this.feePlanId = feePlanId;
        return this;
    }

    public EnumPositionStatus getStatus() {
        return status;
    }

    public PositionDto setStatus(EnumPositionStatus status) {
        this.status = status;
        return this;
    }

    public EnumPositionDirection getDirection() {
        return direction;
    }

    public PositionDto setDirection(EnumPositionDirection direction) {
        this.direction = direction;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public PositionDto setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getAverageCost() {
        return averageCost;
    }

    public PositionDto setAverageCost(BigDecimal averageCost) {
        this.averageCost = averageCost;
        return this;
    }

    public BigDecimal getBalanceCost() {
        return balanceCost;
    }

    public PositionDto setBalanceCost(BigDecimal balanceCost) {
        this.balanceCost = balanceCost;
        return this;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public PositionDto setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
        return this;
    }

    public LocalDateTime getOpenAt() {
        return openAt;
    }

    public PositionDto setOpenAt(LocalDateTime openAt) {
        this.openAt = openAt;
        return this;
    }

    public LocalDateTime getCloseAt() {
        return closeAt;
    }

    public PositionDto setCloseAt(LocalDateTime closeAt) {
        this.closeAt = closeAt;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public PositionDto setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
