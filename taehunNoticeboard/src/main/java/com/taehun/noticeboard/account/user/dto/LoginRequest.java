package com.taehun.noticeboard.account.user.dto;

import com.taehun.noticeboard.admin.login.dto.AdminLoginRequest;

public class LoginRequest {
    private String id;
    private String email;
    private String password;
    private String checkPassword;

    public static LoginRequest createLoginRequest(AdminLoginRequest request) {
        return builder().id(request.getId()).password(request.getPassword()).checkPassword(request.getPassword()).build();
    }

    public static LoginRequestBuilder builder() {
        return new LoginRequestBuilder();
    }

    public String getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getCheckPassword() {
        return this.checkPassword;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setCheckPassword(final String checkPassword) {
        this.checkPassword = checkPassword;
    }

    public LoginRequest(final String id, final String email, final String password, final String checkPassword) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.checkPassword = checkPassword;
    }

    public LoginRequest() {
    }

    public static class LoginRequestBuilder {
        private String id;
        private String email;
        private String password;
        private String checkPassword;

        LoginRequestBuilder() {
        }

        public LoginRequestBuilder id(final String id) {
            this.id = id;
            return this;
        }

        public LoginRequestBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public LoginRequestBuilder password(final String password) {
            this.password = password;
            return this;
        }

        public LoginRequestBuilder checkPassword(final String checkPassword) {
            this.checkPassword = checkPassword;
            return this;
        }

        public LoginRequest build() {
            return new LoginRequest(this.id, this.email, this.password, this.checkPassword);
        }

        public String toString() {
            return "LoginRequest.LoginRequestBuilder(id=" + this.id + ", email=" + this.email + ", password=" + this.password + ", checkPassword=" + this.checkPassword + ")";
        }
    }
}
