package com.taehun.noticeboard.admin.authority.repository;

import com.taehun.noticeboard.admin.authority.dto.AuthorityResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CustomAuthorityRepository {
    List<AuthorityResponse> findAllAuthorityUser(Pageable pageable);

    List<AuthorityResponse> findAllAuthorityUserById(Pageable pageable, String search);
}
