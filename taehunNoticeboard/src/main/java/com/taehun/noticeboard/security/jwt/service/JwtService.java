package com.taehun.noticeboard.security.jwt.service;

import com.taehun.noticeboard.common.response.ResponseCode;
import com.taehun.noticeboard.common.response.ResponseMessage;
import com.taehun.noticeboard.security.exception.TokenForgeryException;
import com.taehun.noticeboard.security.jwt.domain.RefreshToken;
import com.taehun.noticeboard.security.jwt.dto.Token;
import com.taehun.noticeboard.security.jwt.repository.RefreshTokenRepository;
import com.taehun.noticeboard.security.jwt.support.CookieSupport;
import com.taehun.noticeboard.security.jwt.support.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JwtService {
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void login(Token token) {
        RefreshToken refreshToken = RefreshToken.creareRefreshToken(token);
        String loginUserEmail = refreshToken.getKeyEmail();
        this.refreshTokenRepository.existsByKeyEmail(loginUserEmail).ifPresent((a) -> {
            this.refreshTokenRepository.deleteByKeyEmail(loginUserEmail);
        });
        this.refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken getRefreshToken(HttpServletRequest request) {
        String refreshToken = this.getRefreshTokenFromHeader(request);
        return (RefreshToken)this.refreshTokenRepository.findByToken(refreshToken).orElseThrow(() -> {
            return new TokenForgeryException("알 수 없는 RefreshToken 입니다.");
        });
    }

    public ResponseMessage validateRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            RefreshToken token = this.getRefreshToken(request);
            String accessToken = this.jwtTokenProvider.validateRefreshToken(token);
            response.addHeader("Set-Cookie", CookieSupport.createAccessToken(accessToken).toString());
            return ResponseMessage.of(ResponseCode.CREATE_ACCESS_TOKEN);
        } catch (NoSuchElementException var5) {
            CookieSupport.deleteJwtTokenInCookie(response);
            throw new TokenForgeryException("변조된 RefreshToken 입니다.");
        }
    }

    public String getRefreshTokenFromHeader(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            return (String)Arrays.stream(cookies).filter((c) -> {
                return c.getName().equals("refreshToken");
            }).findFirst().map(Cookie::getValue).orElseThrow(() -> {
                return new SecurityException("인증되지 않은 사용자입니다.");
            });
        } else {
            throw new SecurityException("인증되지 않은 사용자입니다.");
        }
    }

    public JwtService(final JwtTokenProvider jwtTokenProvider, final RefreshTokenRepository refreshTokenRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenRepository = refreshTokenRepository;
    }
}