package com.taehun.noticeboard.admin.login.dto;

public class AdminLoginRequest {
    private String id;
    private String password;

    public AdminLoginRequest() {
    }

    public String getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }
}