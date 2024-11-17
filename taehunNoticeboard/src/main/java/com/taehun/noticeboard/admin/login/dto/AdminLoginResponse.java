package com.taehun.noticeboard.admin.login.dto;

import com.taehun.noticeboard.account.user.domain.User;

public class AdminLoginResponse {
    private String id;
    private String name;

    public static AdminLoginResponse createResponse(User user) {
        return builder().id(user.getId()).name(user.getUsername()).build();
    }

    AdminLoginResponse(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static AdminLoginResponseBuilder builder() {
        return new AdminLoginResponseBuilder();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public static class AdminLoginResponseBuilder {
        private String id;
        private String name;

        AdminLoginResponseBuilder() {
        }

        public AdminLoginResponseBuilder id(final String id) {
            this.id = id;
            return this;
        }

        public AdminLoginResponseBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public AdminLoginResponse build() {
            return new AdminLoginResponse(this.id, this.name);
        }

        public String toString() {
            return "AdminLoginResponse.AdminLoginResponseBuilder(id=" + this.id + ", name=" + this.name + ")";
        }
    }
}
