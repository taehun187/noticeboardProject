package com.taehun.noticeboard.account.user.controller;

import com.taehun.noticeboard.account.user.dto.LoginRequest;
import com.taehun.noticeboard.account.user.dto.PasswordRequest;
import com.taehun.noticeboard.account.user.service.LoginService;
import com.taehun.noticeboard.common.response.ResponseCode;
import com.taehun.noticeboard.common.response.ResponseMessage;
import com.taehun.noticeboard.security.jwt.support.CookieSupport;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.security.auth.login.AccountException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class LoginController {
    private final LoginService loginService;

    @PostMapping({"/registers"})
    public ResponseEntity<ResponseMessage> register(@RequestPart(name = "data") LoginRequest loginRequest, @RequestPart(name = "image") MultipartFile multipartFile) throws AccountException, IOException {
        return ResponseEntity.ok().body(this.loginService.register(loginRequest, multipartFile));
    }

    @PatchMapping({"/password"})
    public ResponseEntity modifyPassword(@RequestBody PasswordRequest request) {
        this.loginService.modifyPassword(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping({"/profile-image"})
    public ResponseEntity modifyProfileImage(@RequestPart(name = "image") MultipartFile multipartFile, @CookieValue String accessToken) throws IOException {
        String result = this.loginService.modifyProfileImage(accessToken, multipartFile);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping({"/logins"})
    public ResponseEntity<ResponseMessage> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        this.loginService.login(request, response);
        return ResponseEntity.ok().body(ResponseMessage.of(ResponseCode.LOGIN_SUCCESS));
    }

    @PostMapping({"/user/logout"})
    public ResponseEntity logout(HttpServletResponse response) {
        CookieSupport.deleteJwtTokenInCookie(response);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping({"/users"})
    public ResponseEntity deleteUser(@CookieValue String accessToken, HttpServletResponse response) {
        this.loginService.removeUser(accessToken, response);
        return ResponseEntity.noContent().build();
    }

    @GetMapping({"/users/{email}/{id}"})
    public ResponseEntity<ResponseMessage> isExistAccount(@PathVariable String email, @PathVariable String id) {
        this.loginService.isExistAccount(email, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping({"/users"})
    public ResponseEntity<ResponseMessage> findUserByToken(@CookieValue String accessToken) {
        return ResponseEntity.ok().body(this.loginService.findUserByToken(accessToken));
    }

    @GetMapping({"/logout/message"})
    public ResponseEntity<ResponseMessage> logout() {
        return ResponseEntity.ok().body(ResponseMessage.of(ResponseCode.LOGOUT_SUCCESS));
    }

    public LoginController(final LoginService loginService) {
        this.loginService = loginService;
    }
}