package com.taehun.noticeboard.oauth.support;

import com.taehun.noticeboard.account.user.constant.UserRole;
import com.taehun.noticeboard.oauth.dto.UserSession;
import com.taehun.noticeboard.security.jwt.dto.Token;
import com.taehun.noticeboard.security.jwt.support.CookieSupport;
import com.taehun.noticeboard.security.jwt.support.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;
    private final HttpSession httpSession;

    public OAuth2AuthenticationSuccessHandler(final JwtTokenProvider jwtTokenProvider, final HttpSession httpSession) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.httpSession = httpSession;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserSession user = (UserSession) httpSession.getAttribute("user");
        if (user == null) {
            getRedirectStrategy().sendRedirect(request, response, createRedirectUrl("http://localhost:3000/oauth2/disallowance"));
        } else {
            Token token = jwtTokenProvider.createJwtToken(user.getId(), UserRole.USER);
            CookieSupport.setCookieFromJwt(token, response);
            httpSession.removeAttribute("user");
            getRedirectStrategy().sendRedirect(request, response, createRedirectUrl("http://localhost:3000"));
        }
    }

    private String createRedirectUrl(String url) {
        return UriComponentsBuilder.fromUriString(url).build().toUriString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OAuth2AuthenticationSuccessHandler)) return false;
        OAuth2AuthenticationSuccessHandler that = (OAuth2AuthenticationSuccessHandler) o;
        return jwtTokenProvider.equals(that.jwtTokenProvider) && httpSession.equals(that.httpSession);
    }

    @Override
    public int hashCode() {
        int result = jwtTokenProvider != null ? jwtTokenProvider.hashCode() : 0;
        result = 31 * result + (httpSession != null ? httpSession.hashCode() : 0);
        return result;
    }
}
