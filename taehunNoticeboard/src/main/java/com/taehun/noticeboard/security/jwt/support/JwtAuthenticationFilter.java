package com.taehun.noticeboard.security.jwt.support;

import com.taehun.noticeboard.common.exception.FilterExceptionHandler;
import com.taehun.noticeboard.common.response.ResponseCode;
import com.taehun.noticeboard.security.jwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtService jwtService;

    public JwtAuthenticationFilter(final JwtTokenProvider jwtTokenProvider, final JwtService jwtService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = getAccessTokenFromHeader(request);
        if (token != null && jwtTokenProvider.validateAccessToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else if (token != null) {
            jwtService.validateRefreshToken((HttpServletRequest) request, (HttpServletResponse) response);
            FilterExceptionHandler.setSuccessResponse((HttpServletResponse) response, ResponseCode.CREATE_ACCESS_TOKEN);
            return;
        }

        chain.doFilter(request, response);
    }

    private String getAccessTokenFromHeader(ServletRequest request) {
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        return cookies != null && cookies.length != 0
            ? Arrays.stream(cookies)
                .filter(c -> c.getName().equals("accessToken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null)
            : null;
    }
}
