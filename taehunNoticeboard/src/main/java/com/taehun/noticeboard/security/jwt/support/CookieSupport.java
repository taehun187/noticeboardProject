package com.taehun.noticeboard.security.jwt.support;

import com.taehun.noticeboard.security.jwt.dto.Token;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;

public class CookieSupport {
    @Value("${server.url}")
    private static String DOMAIN_URL;

    public static ResponseCookie createAccessToken(String access) {
        return ResponseCookie.from("accessToken", access).path("/").maxAge(1800000L).secure(true).domain(DOMAIN_URL).httpOnly(true).sameSite("none").build();
    }

    public static ResponseCookie createRefreshToken(String refresh) {
        return ResponseCookie.from("refreshToken", refresh).path("/").maxAge(1209600000L).secure(true).domain(DOMAIN_URL).httpOnly(true).sameSite("none").build();
    }

    public static void setCookieFromJwt(Token token, HttpServletResponse response) {
        response.addHeader("Set-Cookie", createAccessToken(token.getAccessToken()).toString());
        response.addHeader("Set-Cookie", createRefreshToken(token.getRefreshToken()).toString());
    }

    public static void deleteJwtTokenInCookie(HttpServletResponse response) {
        Cookie accessToken = new Cookie("accessToken", (String)null);
        accessToken.setPath("/");
        accessToken.setMaxAge(0);
        accessToken.setDomain(DOMAIN_URL);
        Cookie refreshToken = new Cookie("refreshToken", (String)null);
        refreshToken.setPath("/");
        refreshToken.setMaxAge(0);
        refreshToken.setDomain(DOMAIN_URL);
        response.addCookie(accessToken);
        response.addCookie(refreshToken);
    }

    private CookieSupport() {
    }
}