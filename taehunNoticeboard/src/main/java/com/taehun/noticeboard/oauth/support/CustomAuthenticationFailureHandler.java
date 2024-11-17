package com.taehun.noticeboard.oauth.support;

import com.taehun.noticeboard.common.exception.FilterExceptionHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    public CustomAuthenticationFailureHandler() {
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        FilterExceptionHandler.setErrorResponse(response, HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
        String errorMessage = URLEncoder.encode(exception.getMessage(), "UTF-8");
        this.getRedirectStrategy().sendRedirect(request, response, this.createRedirectUrl("http://localhost:3030/oauth/error?message=" + errorMessage));
    }

    public String createRedirectUrl(String url) {
        return UriComponentsBuilder.fromUriString(url).build().toUriString();
    }
}