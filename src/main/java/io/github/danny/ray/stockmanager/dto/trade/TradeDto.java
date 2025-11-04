package io.github.danny.ray.stockmanager.dto.trade;

import java.math.BigDecimal;

import io.github.danny.ray.stockmanager.model.enums.EnumTradeType;

/**
 * for create trade
 */
public class TradeDto {

    private int productId;

    private int quantity;

    private BigDecimal price;

    private EnumTradeType tradeType;

    private int feePlanId;

    private String comment;

    public TradeDto() {
    }

    public TradeDto(int productId, int quantity, BigDecimal price, EnumTradeType tradeType, int feePlanId, String comment) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.tradeType = tradeType;
        this.feePlanId = feePlanId;
        this.comment = comment;
    }

    public int getProductId() {
        return productId;
    }

    public TradeDto setProductId(int productId) {
        this.productId = productId;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public TradeDto setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public TradeDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public EnumTradeType getTradeType() {
        return tradeType;
    }

    public TradeDto setTradeType(EnumTradeType tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    public int getFeePlanId() {
        return feePlanId;
    }

    public TradeDto setFeePlanId(int feePlanId) {
        this.feePlanId = feePlanId;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public TradeDto setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
