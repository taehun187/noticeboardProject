package com.taehun.noticeboard.admin.authority.dto;

public class SuspensionRequest {
    private long userKey;
    private int suspensionDate;
    private String reason;

    public SuspensionRequest() {
    }

    public long getUserKey() {
        return this.userKey;
    }

    public int getSuspensionDate() {
        return this.suspensionDate;
    }

    public String getReason() {
        return this.reason;
    }
}
