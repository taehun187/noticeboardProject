package com.taehun.noticeboard.security.jwt.dto;

public class Token {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private String key;

    public static TokenBuilder builder() {
        return new TokenBuilder();
    }

    public String getGrantType() {
        return this.grantType;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public String getKey() {
        return this.key;
    }

    public void setGrantType(final String grantType) {
        this.grantType = grantType;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public Token() {
    }

    public Token(final String grantType, final String accessToken, final String refreshToken, final String key) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.key = key;
    }

    public static class TokenBuilder {
        private String grantType;
        private String accessToken;
        private String refreshToken;
        private String key;

        TokenBuilder() {
        }

        public TokenBuilder grantType(final String grantType) {
            this.grantType = grantType;
            return this;
        }

        public TokenBuilder accessToken(final String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public TokenBuilder refreshToken(final String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public TokenBuilder key(final String key) {
            this.key = key;
            return this;
        }

        public Token build() {
            return new Token(this.grantType, this.accessToken, this.refreshToken, this.key);
        }

        public String toString() {
            return "Token.TokenBuilder(grantType=" + this.grantType + ", accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ", key=" + this.key + ")";
        }
    }
}
