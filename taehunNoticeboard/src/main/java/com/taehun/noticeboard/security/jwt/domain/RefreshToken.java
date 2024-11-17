package com.taehun.noticeboard.security.jwt.domain;

import com.taehun.noticeboard.security.jwt.dto.Token;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    @Column(
        nullable = false
    )
    private Long tokenId;
    @Column(
        nullable = false
    )
    private String token;
    @Column(
        nullable = false
    )
    private String keyEmail;

    public static RefreshToken defaultRefreshToken() {
        return builder().tokenId(1L).token(" ").keyEmail(" ").build();
    }

    public static RefreshToken creareRefreshToken(Token token) {
        return builder().keyEmail(token.getKey()).token(token.getRefreshToken()).build();
    }

    public static RefreshTokenBuilder builder() {
        return new RefreshTokenBuilder();
    }

    public Long getTokenId() {
        return this.tokenId;
    }

    public String getToken() {
        return this.token;
    }

    public String getKeyEmail() {
        return this.keyEmail;
    }

    public RefreshToken() {
    }

    public RefreshToken(final Long tokenId, final String token, final String keyEmail) {
        this.tokenId = tokenId;
        this.token = token;
        this.keyEmail = keyEmail;
    }

    public static class RefreshTokenBuilder {
        private Long tokenId;
        private String token;
        private String keyEmail;

        RefreshTokenBuilder() {
        }

        public RefreshTokenBuilder tokenId(final Long tokenId) {
            this.tokenId = tokenId;
            return this;
        }

        public RefreshTokenBuilder token(final String token) {
            this.token = token;
            return this;
        }

        public RefreshTokenBuilder keyEmail(final String keyEmail) {
            this.keyEmail = keyEmail;
            return this;
        }

        public RefreshToken build() {
            return new RefreshToken(this.tokenId, this.token, this.keyEmail);
        }

        public String toString() {
            return "RefreshToken.RefreshTokenBuilder(tokenId=" + this.tokenId + ", token=" + this.token + ", keyEmail=" + this.keyEmail + ")";
        }
    }
}