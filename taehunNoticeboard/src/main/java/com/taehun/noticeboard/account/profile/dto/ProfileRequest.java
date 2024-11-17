package com.taehun.noticeboard.account.profile.dto;

public class ProfileRequest {
    private String userId;
    private String phone;
    private Integer options;

    public static ProfileRequestBuilder builder() {
        return new ProfileRequestBuilder();
    }

    public String getUserId() {
        return this.userId;
    }

    public String getPhone() {
        return this.phone;
    }

    public Integer getOptions() {
        return this.options;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public void setOptions(final Integer options) {
        this.options = options;
    }

    public ProfileRequest(final String userId, final String phone, final Integer options) {
        this.userId = userId;
        this.phone = phone;
        this.options = options;
    }

    public ProfileRequest() {
    }

    public static class ProfileRequestBuilder {
        private String userId;
        private String phone;
        private Integer options;

        ProfileRequestBuilder() {
        }

        public ProfileRequestBuilder userId(final String userId) {
            this.userId = userId;
            return this;
        }

        public ProfileRequestBuilder phone(final String phone) {
            this.phone = phone;
            return this;
        }

        public ProfileRequestBuilder options(final Integer options) {
            this.options = options;
            return this;
        }

        public ProfileRequest build() {
            return new ProfileRequest(this.userId, this.phone, this.options);
        }

        public String toString() {
            return "ProfileRequest.ProfileRequestBuilder(userId=" + this.userId + ", phone=" + this.phone + ", options=" + this.options + ")";
        }
    }
}
