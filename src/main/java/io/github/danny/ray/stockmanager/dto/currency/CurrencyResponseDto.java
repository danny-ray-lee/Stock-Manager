package io.github.danny.ray.stockmanager.dto.currency;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class CurrencyResponseDto {

    private String msg;

    private int status;

    private List<CurrencyDto> data;

    @JsonIgnore
    private CurrencyDto latestCurrencyDto;

    @JsonCreator
    public CurrencyResponseDto(
            @JsonProperty("msg") String msg,
            @JsonProperty("status") int status,
            @JsonProperty("data") List<CurrencyDto> data) {
        this.msg = msg;
        this.status = status;
        this.data = data;
        if (data != null && !data.isEmpty()) {
            this.latestCurrencyDto = data.stream()
                    .max(Comparator.comparing(CurrencyDto::getDate))
                    .orElse(null);
        }
    }

    public CurrencyResponseDto() {
    }

    public BigDecimal getCashBuy() {
        return BigDecimal.valueOf(latestCurrencyDto.getCashBuy());
    }

    public BigDecimal getCashSell() {
        return BigDecimal.valueOf(latestCurrencyDto.getCashSell());
    }

    public BigDecimal getSpotBuy() {
        return BigDecimal.valueOf(latestCurrencyDto.getSpotBuy());
    }

    public BigDecimal getSpotSell() {
        return BigDecimal.valueOf(latestCurrencyDto.getSpotSell());
    }

    public String getMsg() {
        return msg;
    }

    public CurrencyResponseDto setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public CurrencyResponseDto setStatus(int status) {
        this.status = status;
        return this;
    }

    public List<CurrencyDto> getData() {
        return data;
    }

    public CurrencyResponseDto setData(List<CurrencyDto> data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "CurrencyResponseDto{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                ", data=" + data +
                ", latestCurrencyDto=" + latestCurrencyDto +
                '}';
    }
}
