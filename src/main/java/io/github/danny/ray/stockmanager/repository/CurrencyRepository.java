package io.github.danny.ray.stockmanager.repository;

import java.util.Optional;

import io.github.danny.ray.stockmanager.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 貨幣資料存取層
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {

    /**
     * 根據貨幣代碼查詢貨幣
     *
     * @param symbol 貨幣代碼
     * @return 貨幣實體
     */
    Optional<Currency> findBySymbol(String symbol);
}
