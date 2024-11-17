package com.taehun.noticeboard.admin.authority.dto;

import com.taehun.noticeboard.account.user.constant.UserRole;

public class ModifyRoleRequest {
    private long userKey;
    private UserRole userRole;

    public long getUserKey() {
        return this.userKey;
    }

    public UserRole getUserRole() {
        return this.userRole;
    }

    public ModifyRoleRequest(final long userKey, final UserRole userRole) {
        this.userKey = userKey;
        this.userRole = userRole;
    }
}
