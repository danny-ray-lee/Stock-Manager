package io.github.danny.ray.stockmanager.repository;

import java.util.Optional;

import io.github.danny.ray.stockmanager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 商品資料存取層
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findBySymbol(String symbol);
}
