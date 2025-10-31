package io.github.danny.ray.stockmanager.repository;

import java.util.Optional;

import io.github.danny.ray.stockmanager.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 使用者資料存取層
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUsername(String username);

}
