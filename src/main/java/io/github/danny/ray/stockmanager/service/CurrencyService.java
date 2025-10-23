package io.github.danny.ray.stockmanager.service;

import java.time.LocalDate;

import io.github.danny.ray.stockmanager.model.Currency.CurrencyType;
import okhttp3.Request;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    private static final String dataSet = "TaiwanExchangeRate";

    private static final String url = "https://api.finmindtrade.com/api/v3/data";

    public void requestAllCurrency() {
        for (CurrencyType type : CurrencyType.values()) {
            String sb = url + "?" +
                    "dataset=" + dataSet + "&" +
                    "data_id=" + type.name() + "&" +
                    "date" + LocalDate.now().minusDays(1);

            Request request = new Request.Builder().url(sb).build();


        }
    }

}
