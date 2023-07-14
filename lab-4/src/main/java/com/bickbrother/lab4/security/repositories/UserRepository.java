package com.bickbrother.lab4.security.repositories;

import com.bickbrother.lab4.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByBrand_Id(Long id);
    Optional<User> findByUsername(String username);
}
