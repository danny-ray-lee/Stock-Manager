package io.github.danny.ray.stockmanager.repository;

import java.util.List;
import java.util.Optional;

import io.github.danny.ray.stockmanager.model.Positions;
import io.github.danny.ray.stockmanager.model.Products;
import io.github.danny.ray.stockmanager.model.Users;
import io.github.danny.ray.stockmanager.model.enums.EnumPositionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 持倉資料存取層
 */
@Repository
public interface PositionRepository extends JpaRepository<Positions, Integer> {

    List<Positions> findByUser(Users users);

    List<Positions> findByUserAndStatus(Users users, EnumPositionStatus status);

    List<Positions> findByUserAndProduct(Users users, Products products);

    List<Positions> findByUserAndProductAndStatus(Users users, Products products, EnumPositionStatus status);

    @Query("SELECT p FROM Position p WHERE p.user = :user AND p.product = :product AND p.status = 'OPEN'")
    Optional<Positions> findByUserAndProductAndStatusOpen(Users users, Products products);

}
