package com.taehun.noticeboard.smtp.dto;

public class CertRequest {
    private String email;
    private String code;

    public CertRequest() {
    }

    public String getEmail() {
        return this.email;
    }

    public String getCode() {
        return this.code;
    }
}