package com.taehun.noticeboard.security.jwt.repository;

import com.taehun.noticeboard.security.jwt.domain.RefreshToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Query("SELECT p from RefreshToken p where p.keyEmail = :userEmail")
    Optional<RefreshToken> existsByKeyEmail(@Param("userEmail") String userEmail);

    void deleteByKeyEmail(String userEmail);
}