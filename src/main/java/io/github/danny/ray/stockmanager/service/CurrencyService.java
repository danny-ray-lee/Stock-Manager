package io.github.danny.ray.stockmanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.danny.ray.stockmanager.dto.currency.CurrencyResponseDto;
import io.github.danny.ray.stockmanager.model.Currency;
import io.github.danny.ray.stockmanager.model.Currency.CurrencySymbol;
import io.github.danny.ray.stockmanager.repository.CurrencyRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyService {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyService.class);

    private static final String dataSet = "TaiwanExchangeRate";

    private static final String url = "https://api.finmindtrade.com/api/v3/data";

    private final OkHttpClient client;

    private final ObjectMapper objectMapper;

    private final CurrencyRepository repository;

    private final Map<CurrencySymbol, Currency> currencyCache = new HashMap<>();

    public CurrencyService(OkHttpClient client, ObjectMapper objectMapper, CurrencyRepository repository) {
        this.client = client;
        this.objectMapper = objectMapper;
        this.repository = repository;

        this.repository.findAll().forEach(currency -> currencyCache.put(currency.getSymbol(), currency));

        requestAllCurrency();
    }

    public void requestAllCurrency() {
        for (CurrencySymbol symbol : CurrencySymbol.values()) {

            if (symbol == CurrencySymbol.TWD) {
                continue;
            }

            Currency currency = currencyCache.getOrDefault(symbol, new Currency(symbol));
            LocalDateTime lastUpdateAt = currency.getLastUpdateAt();
            if (lastUpdateAt != null && !lastUpdateAt.toLocalDate().isBefore(LocalDate.now())) {
                continue;
            }

            String sb = url + "?" +
                    "dataset=" + dataSet + "&" +
                    "data_id=" + symbol.name() + "&" +
                    "date=" + LocalDate.now().minusDays(1);

            Request request = new Request.Builder().url(sb).build();

            try (Response response = client.newCall(request).execute()) {

                CurrencyResponseDto currencyResponseDto = objectMapper.readValue(response.body().string(), CurrencyResponseDto.class);
                if (currencyResponseDto.getData() == null || currencyResponseDto.getData().isEmpty()) {
                    logger.warn("No Data Fetched For Currency 【{}】", symbol);
                    continue;
                }
                currency.setCashBuy(currencyResponseDto.getSpotBuy());
                currency.setCashSell(currencyResponseDto.getSpotSell());
                currency.setSpotBuy(currencyResponseDto.getSpotBuy());
                currency.setSpotSell(currencyResponseDto.getSpotSell());

                updateCurrency(currency);

            } catch (IOException e) {
                logger.warn("Fetch Currency 【{}】 Error", symbol, e);
            }
        }
    }

    public void updateCurrency(Currency currency) {
        Currency newCurrency = repository.save(currency);
        currencyCache.put(newCurrency.getSymbol(), newCurrency);
    }

}
