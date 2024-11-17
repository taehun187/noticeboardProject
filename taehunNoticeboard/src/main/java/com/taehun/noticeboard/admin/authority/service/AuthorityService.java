package com.taehun.noticeboard.admin.authority.service;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.account.user.repository.LoginRepository;
import com.taehun.noticeboard.admin.authority.dto.AuthorityResponse;
import com.taehun.noticeboard.admin.authority.dto.ModifyRoleRequest;
import com.taehun.noticeboard.admin.authority.dto.SuspensionRequest;
import com.taehun.noticeboard.admin.authority.repository.AuthorityRepository;
import java.util.List;
import javax.security.auth.login.AccountException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorityService {
    private final AuthorityRepository authorityRepository;
    private final LoginRepository loginRepository;

    public Long findNumberOfAllUsers(String search) {
        return search == null ? this.authorityRepository.countBy() : this.authorityRepository.countByIdContaining(search);
    }

    public List<AuthorityResponse> findAllAuthorityUser(Pageable pageable, String search) {
        return search == null ? this.authorityRepository.findAllAuthorityUser(pageable) : this.authorityRepository.findAllAuthorityUserById(pageable, search);
    }

    @Transactional
    public void modifyAuthorityUserRole(ModifyRoleRequest request) throws AccountException {
        User user = (User)this.loginRepository.findById(request.getUserKey()).orElseThrow(() -> {
            return new AccountException("사용자 정보를 찾을 수 없습니다.");
        });
        user.setRole(request.getUserRole());
    }

    @Transactional
    public void modifySuspensionOfUse(SuspensionRequest request) throws AccountException {
        User user = (User)this.loginRepository.findById(request.getUserKey()).orElseThrow(() -> {
            return new AccountException("사용자 정보를 찾을 수 없습니다.");
        });
        this.modifySuspensionDate(user, request.getSuspensionDate(), request.getReason());
    }

    private void modifySuspensionDate(User user, int date, String reason) {
        if (date > 0) {
            user.addSuspensionDate(date, reason);
        } else {
            user.minusSuspensionDate(date * -1);
        }
    }

    public AuthorityService(final AuthorityRepository authorityRepository, final LoginRepository loginRepository) {
        this.authorityRepository = authorityRepository;
        this.loginRepository = loginRepository;
    }
}
