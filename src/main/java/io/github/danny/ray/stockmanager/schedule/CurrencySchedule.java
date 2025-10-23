package io.github.danny.ray.stockmanager.schedule;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class CurrencySchedule {

    // 每小時一次
    @Scheduled(cron = "0 0 * * * ?")
    public void updateCurrency() {

    }

}
