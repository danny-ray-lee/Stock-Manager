package io.github.danny.ray.stockmanager.dto.currency;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CurrencyDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
