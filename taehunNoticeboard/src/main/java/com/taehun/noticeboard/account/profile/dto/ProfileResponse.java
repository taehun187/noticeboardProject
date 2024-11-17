package com.taehun.noticeboard.account.profile.dto;

import com.taehun.noticeboard.account.profile.domain.Profile;
import com.taehun.noticeboard.account.user.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDate;

public class ProfileResponse {
    private String email;
    private String phone;
    private int options;
    @JsonFormat(
        shape = Shape.STRING,
        pattern = "yyyy-MM-dd",
        timezone = "Asia/Seoul"
    )
    private LocalDate joinDate;

    public static ProfileResponse createProfileResponse(Profile profile, User user) {
        return builder().email(user.getEmail()).phone(profile.getPhone()).options(profile.getOptions()).joinDate(user.getJoinDate()).build();
    }

    public static ProfileResponseBuilder builder() {
        return new ProfileResponseBuilder();
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public int getOptions() {
        return this.options;
    }

    public LocalDate getJoinDate() {
        return this.joinDate;
    }

    private ProfileResponse() {
    }

    private ProfileResponse(final String email, final String phone, final int options, final LocalDate joinDate) {
        this.email = email;
        this.phone = phone;
        this.options = options;
        this.joinDate = joinDate;
    }

    public static class ProfileResponseBuilder {
        private String email;
        private String phone;
        private int options;
        private LocalDate joinDate;

        ProfileResponseBuilder() {
        }

        public ProfileResponseBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public ProfileResponseBuilder phone(final String phone) {
            this.phone = phone;
            return this;
        }

        public ProfileResponseBuilder options(final int options) {
            this.options = options;
            return this;
        }

        @JsonFormat(
            shape = Shape.STRING,
            pattern = "yyyy-MM-dd",
            timezone = "Asia/Seoul"
        )
        public ProfileResponseBuilder joinDate(final LocalDate joinDate) {
            this.joinDate = joinDate;
            return this;
        }

        public ProfileResponse build() {
            return new ProfileResponse(this.email, this.phone, this.options, this.joinDate);
        }

        public String toString() {
            return "ProfileResponse.ProfileResponseBuilder(email=" + this.email + ", phone=" + this.phone + ", options=" + this.options + ", joinDate=" + this.joinDate + ")";
        }
    }
}