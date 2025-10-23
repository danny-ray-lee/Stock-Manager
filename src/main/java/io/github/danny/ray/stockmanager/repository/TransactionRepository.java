package io.github.danny.ray.stockmanager.repository;

import io.github.danny.ray.stockmanager.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 交易記錄資料存取層
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
