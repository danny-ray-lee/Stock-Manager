package io.github.danny.ray.stockmanager.repository;

import io.github.danny.ray.stockmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 使用者資料存取層
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
