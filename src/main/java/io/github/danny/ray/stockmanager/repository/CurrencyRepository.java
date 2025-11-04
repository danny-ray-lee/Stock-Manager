package io.github.danny.ray.stockmanager.repository;

import io.github.danny.ray.stockmanager.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 貨幣資料存取層
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
