package com.taehun.noticeboard.admin.login.service;

import com.taehun.noticeboard.account.user.constant.UserRole;
import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.account.user.dto.LoginRequest;
import com.taehun.noticeboard.account.user.exception.LoginException;
import com.taehun.noticeboard.account.user.service.LoginService;
import com.taehun.noticeboard.admin.login.dto.AdminLoginRequest;
import com.taehun.noticeboard.admin.login.dto.AdminLoginResponse;
import com.taehun.noticeboard.common.response.message.AccountMessage;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginService {
    private final LoginService loginService;

    public AdminLoginResponse adminLogin(AdminLoginRequest request, HttpServletResponse response) {
        LoginRequest loginRequest = LoginRequest.createLoginRequest(request);
        User result = this.loginService.login(loginRequest, response);
        if (result.getRole() != UserRole.MANAGER) {
            throw new LoginException(AccountMessage.NOT_FOUNT_ACCOUNT);
        } else {
            return AdminLoginResponse.createResponse(result);
        }
    }

    public AdminLoginService(final LoginService loginService) {
        this.loginService = loginService;
    }
}