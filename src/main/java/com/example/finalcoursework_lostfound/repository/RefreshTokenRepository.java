package com.example.finalcoursework_lostfound.repository;


import com.example.finalcoursework_lostfound.entity.RefreshToken;
import com.example.finalcoursework_lostfound.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUserId(Long userId);

    @Modifying
    void deleteByUser(User user);
}