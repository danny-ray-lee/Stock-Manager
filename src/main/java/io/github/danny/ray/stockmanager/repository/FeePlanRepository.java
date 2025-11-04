package io.github.danny.ray.stockmanager.repository;

import io.github.danny.ray.stockmanager.model.fee.FeePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeePlanRepository extends JpaRepository<FeePlan, Integer> {
}
