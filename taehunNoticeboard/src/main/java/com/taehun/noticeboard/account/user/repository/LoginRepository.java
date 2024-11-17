package com.taehun.noticeboard.account.user.repository;

import com.taehun.noticeboard.account.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<User, Long> {
    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);
}
