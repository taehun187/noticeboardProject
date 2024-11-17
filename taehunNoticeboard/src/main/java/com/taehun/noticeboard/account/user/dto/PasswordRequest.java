package com.taehun.noticeboard.account.user.dto;

public class PasswordRequest {
    private String email;
    private String password;

    public PasswordRequest() {
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
