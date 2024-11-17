package com.taehun.noticeboard.admin.authority.dto;

import com.taehun.noticeboard.account.user.constant.UserRole;
import java.time.LocalDate;

public class AuthorityResponse {
    private long userKey;
    private String id;
    private LocalDate joinDate;
    private LocalDate lastLoginDate;
    private String suspensionReason;
    private LocalDate suspensionDate;
    private boolean isSuspension;
    private UserRole userRole;

    public long getUserKey() {
        return this.userKey;
    }

    public String getId() {
        return this.id;
    }

    public LocalDate getJoinDate() {
        return this.joinDate;
    }

    public LocalDate getLastLoginDate() {
        return this.lastLoginDate;
    }

    public String getSuspensionReason() {
        return this.suspensionReason;
    }

    public LocalDate getSuspensionDate() {
        return this.suspensionDate;
    }

    public boolean isSuspension() {
        return this.isSuspension;
    }

    public UserRole getUserRole() {
        return this.userRole;
    }

    public AuthorityResponse(final long userKey, final String id, final LocalDate joinDate, final LocalDate lastLoginDate, final String suspensionReason, final LocalDate suspensionDate, final boolean isSuspension, final UserRole userRole) {
        this.userKey = userKey;
        this.id = id;
        this.joinDate = joinDate;
        this.lastLoginDate = lastLoginDate;
        this.suspensionReason = suspensionReason;
        this.suspensionDate = suspensionDate;
        this.isSuspension = isSuspension;
        this.userRole = userRole;
    }
}
