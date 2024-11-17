package com.taehun.noticeboard.admin.login.controller;

import com.taehun.noticeboard.admin.login.dto.AdminLoginRequest;
import com.taehun.noticeboard.admin.login.service.AdminLoginService;
import jakarta.servlet.http.HttpServletResponse;
import javax.security.auth.login.AccountException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/admin"})
public class AdminLoginController {
    private final AdminLoginService adminLoginService;

    @PostMapping({"/login"})
    public ResponseEntity adminLogin(@RequestBody AdminLoginRequest request, HttpServletResponse response) throws AccountException {
        return ResponseEntity.ok().body(this.adminLoginService.adminLogin(request, response));
    }

    public AdminLoginController(final AdminLoginService adminLoginService) {
        this.adminLoginService = adminLoginService;
    }
}
