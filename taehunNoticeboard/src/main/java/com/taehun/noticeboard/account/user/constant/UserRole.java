package com.taehun.noticeboard.account.user.constant;

public enum UserRole {
    USER("ROLE_USER", "일반 사용자"),
    MANAGER("ROLE_MANAGER", "관리자");

    private final String role;
    private final String title;

    public String getRole() {
        return this.role;
    }

    public String getTitle() {
        return this.title;
    }

    private UserRole(final String role, final String title) {
        this.role = role;
        this.title = title;
    }
}
