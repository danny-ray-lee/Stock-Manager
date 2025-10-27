package io.github.danny.ray.stockmanager.schedule;

import io.github.danny.ray.stockmanager.service.CurrencyService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class CurrencySchedule {

    private final CurrencyService currencyService;

    public CurrencySchedule(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    // 每小時一次
    @Scheduled(cron = "0 0 * * * ?")
    public void updateCurrency() {
        currencyService.requestAllCurrency();
    }

}
