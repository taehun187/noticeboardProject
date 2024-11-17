package com.taehun.noticeboard.security.service;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.account.user.repository.LoginRepository;
import com.taehun.noticeboard.common.response.message.AccountMessage;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final LoginRepository loginRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User result = (User)this.loginRepository.findById(username).orElseThrow(() -> {
            return new UsernameNotFoundException(AccountMessage.NOT_FOUNT_ACCOUNT.getMessage());
        });
        return result;
    }

    public CustomUserDetailService(final LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
}