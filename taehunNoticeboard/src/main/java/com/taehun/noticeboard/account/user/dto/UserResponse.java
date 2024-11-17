package com.taehun.noticeboard.account.user.dto;

import com.taehun.noticeboard.account.user.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDate;

public class UserResponse {
    private long userKey;
    private String id;
    private String password;
    @JsonFormat(
        shape = Shape.STRING,
        pattern = "yyyy-MM-dd",
        timezone = "Asia/Seoul"
    )
    private LocalDate joinDate;
    private String profileImage;
    private boolean isDelete;
    private int options;

    public static UserResponse createResponse(User user) {
        return builder().userKey(user.getUserKey()).id(user.getId()).password(user.getPassword()).joinDate(user.getJoinDate()).isDelete(user.isDelete()).profileImage(user.getProfileImage()).options(user.getProfile().getOptions()).build();
    }

    public static UserResponseBuilder builder() {
        return new UserResponseBuilder();
    }

    public long getUserKey() {
        return this.userKey;
    }

    public String getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public LocalDate getJoinDate() {
        return this.joinDate;
    }

    public String getProfileImage() {
        return this.profileImage;
    }

    public boolean isDelete() {
        return this.isDelete;
    }

    public int getOptions() {
        return this.options;
    }

    public UserResponse() {
    }

    public UserResponse(final long userKey, final String id, final String password, final LocalDate joinDate, final String profileImage, final boolean isDelete, final int options) {
        this.userKey = userKey;
        this.id = id;
        this.password = password;
        this.joinDate = joinDate;
        this.profileImage = profileImage;
        this.isDelete = isDelete;
        this.options = options;
    }

    public static class UserResponseBuilder {
        private long userKey;
        private String id;
        private String password;
        private LocalDate joinDate;
        private String profileImage;
        private boolean isDelete;
        private int options;

        UserResponseBuilder() {
        }

        public UserResponseBuilder userKey(final long userKey) {
            this.userKey = userKey;
            return this;
        }

        public UserResponseBuilder id(final String id) {
            this.id = id;
            return this;
        }

        public UserResponseBuilder password(final String password) {
            this.password = password;
            return this;
        }

        @JsonFormat(
            shape = Shape.STRING,
            pattern = "yyyy-MM-dd",
            timezone = "Asia/Seoul"
        )
        public UserResponseBuilder joinDate(final LocalDate joinDate) {
            this.joinDate = joinDate;
            return this;
        }

        public UserResponseBuilder profileImage(final String profileImage) {
            this.profileImage = profileImage;
            return this;
        }

        public UserResponseBuilder isDelete(final boolean isDelete) {
            this.isDelete = isDelete;
            return this;
        }

        public UserResponseBuilder options(final int options) {
            this.options = options;
            return this;
        }

        public UserResponse build() {
            return new UserResponse(this.userKey, this.id, this.password, this.joinDate, this.profileImage, this.isDelete, this.options);
        }

        public String toString() {
            return "UserResponse.UserResponseBuilder(userKey=" + this.userKey + ", id=" + this.id + ", password=" + this.password + ", joinDate=" + this.joinDate + ", profileImage=" + this.profileImage + ", isDelete=" + this.isDelete + ", options=" + this.options + ")";
        }
    }
}
