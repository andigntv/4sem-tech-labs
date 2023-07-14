package com.bickbrother.web.security.repositories;

import com.bickbrother.web.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByBrand_Id(Long id);
    Optional<User> findByUsername(String username);
}
