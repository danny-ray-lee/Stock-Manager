package io.github.danny.ray.stockmanager.dto.transaction;

import java.math.BigDecimal;

import io.github.danny.ray.stockmanager.model.enums.EnumTradeType;

public class TransactionDto {

    private int id = 0;

    private int positionId;

    private EnumTradeType tradeType;

    private BigDecimal price;

    private int quantity;

    private String comment;

    public TransactionDto() {
    }

    public TransactionDto(int id, int positionId, EnumTradeType tradeType, BigDecimal price, int quantity, String comment) {
        this.id = id;
        this.positionId = positionId;
        this.tradeType = tradeType;
        this.price = price;
        this.quantity = quantity;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public TransactionDto setId(int id) {
        this.id = id;
        return this;
    }

    public int getPositionId() {
        return positionId;
    }

    public TransactionDto setPositionId(int positionId) {
        this.positionId = positionId;
        return this;
    }

    public EnumTradeType getTradeType() {
        return tradeType;
    }

    public TransactionDto setTradeType(EnumTradeType tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public TransactionDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public TransactionDto setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public TransactionDto setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
