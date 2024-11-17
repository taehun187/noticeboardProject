package com.taehun.noticeboard.admin.authority.repository;

import com.taehun.noticeboard.account.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<User, Long>, CustomAuthorityRepository {
    long countBy();

    long countByIdContaining(String id);
}
