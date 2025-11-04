package io.github.danny.ray.stockmanager.repository;

import io.github.danny.ray.stockmanager.model.Positions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 持倉資料存取層
 */
@Repository
public interface PositionRepository extends JpaRepository<Positions, Integer> {

}
