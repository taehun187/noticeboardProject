package com.taehun.noticeboard.security.jwt.support;

import com.taehun.noticeboard.account.user.constant.UserRole;
import com.taehun.noticeboard.security.exception.TokenForgeryException;
import com.taehun.noticeboard.security.jwt.domain.RefreshToken;
import com.taehun.noticeboard.security.jwt.dto.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import jakarta.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    private final UserDetailsService loginService;
    private String secretKey = "noticeboardSecretKey";
    private String refreshKey = "noticeboardSecretKey";
    private long accessTokenValidTime = 1800000L;
    private long refreshTokenValidTime = 1209600000L;

    @PostConstruct
    protected void init() {
        this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
        this.refreshKey = Base64.getEncoder().encodeToString(this.refreshKey.getBytes());
    }

    public Token createJwtToken(String userPk, UserRole roles) {
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);
        String accessToken = this.createAccessToken(claims);
        String refreshToken = this.createRefreshToken(claims);
        return Token.builder().accessToken(accessToken).refreshToken(refreshToken).key(userPk).build();
    }

    public String validateRefreshToken(RefreshToken refreshToken) {
        String token = refreshToken.getToken();
        Jws<Claims> claims = Jwts.parser().setSigningKey(this.refreshKey).parseClaimsJws(token);
        return !((Claims)claims.getBody()).getExpiration().before(new Date()) ? this.recreationAccessToken(((Claims)claims.getBody()).get("sub").toString(), ((Claims)claims.getBody()).get("roles")) : null;
    }

    public String recreationAccessToken(String userEmail, Object roles) {
        Claims claims = Jwts.claims().setSubject(userEmail);
        claims.put("roles", roles);
        return this.createAccessToken(claims);
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.loginService.loadUserByUsername(this.getUserPk(token));
        return userDetails == null ? null : new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserPk(String token) {
        return ((Claims)Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody()).getSubject();
    }

    public boolean validateAccessToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(jwtToken);
            return !((Claims)claims.getBody()).getExpiration().before(new Date());
        } catch (ExpiredJwtException var3) {
            return false;
        } catch (SignatureException var4) {
            throw new TokenForgeryException("알 수 없는 토큰이거나 , 변조되었습니다.");
        }
    }

    public String createAccessToken(Claims claims) {
        Date now = new Date();
        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(new Date(now.getTime() + this.accessTokenValidTime)).signWith(SignatureAlgorithm.HS256, this.secretKey).compact();
    }

    public String createRefreshToken(Claims claims) {
        Date now = new Date();
        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(new Date(now.getTime() + this.refreshTokenValidTime)).signWith(SignatureAlgorithm.HS256, this.refreshKey).compact();
    }

    public JwtTokenProvider(final UserDetailsService loginService) {
        this.loginService = loginService;
    }
}