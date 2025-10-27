package io.github.danny.ray.stockmanager.dto.currency;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.danny.ray.stockmanager.model.Currency;

import java.time.LocalDate;

public class CurrencyDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonProperty("currency")
    private Currency.CurrencySymbol currency;

    @JsonProperty("cash_buy")
    private double cashBuy;

    @JsonProperty("cash_sell")
    private double cashSell;

    @JsonProperty("spot_buy")
    private double spotBuy;

    @JsonProperty("spot_sell")
    private double spotSell;

    public LocalDate getDate() {
        return date;
    }

    public CurrencyDto setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Currency.CurrencySymbol getCurrency() {
        return currency;
    }

    public CurrencyDto setCurrency(Currency.CurrencySymbol currency) {
        this.currency = currency;
        return this;
    }

    public double getCashBuy() {
        return cashBuy;
    }

    public CurrencyDto setCashBuy(double cashBuy) {
        this.cashBuy = cashBuy;
        return this;
    }

    public double getCashSell() {
        return cashSell;
    }

    public CurrencyDto setCashSell(double cashSell) {
        this.cashSell = cashSell;
        return this;
    }

    public double getSpotBuy() {
        return spotBuy;
    }

    public CurrencyDto setSpotBuy(double spotBuy) {
        this.spotBuy = spotBuy;
        return this;
    }

    public double getSpotSell() {
        return spotSell;
    }

    public CurrencyDto setSpotSell(double spotSell) {
        this.spotSell = spotSell;
        return this;
    }

    @Override
    public String toString() {
        return "CurrencyDto{" +
                "date=" + date +
                ", currency=" + currency +
                ", cashBuy=" + cashBuy +
                ", cashSell=" + cashSell +
                ", spotBuy=" + spotBuy +
                ", spotSell=" + spotSell +
                '}';
    }
}
