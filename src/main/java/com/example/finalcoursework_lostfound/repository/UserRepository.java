package com.example.finalcoursework_lostfound.repository;


import com.example.finalcoursework_lostfound.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);  // Add this line

    <T> ScopedValue<T> findByEmail(String username);
}