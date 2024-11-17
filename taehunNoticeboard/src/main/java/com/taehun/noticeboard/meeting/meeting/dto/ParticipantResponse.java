package com.taehun.noticeboard.meeting.meeting.dto;

public class ParticipantResponse {
    private long userKey;
    private String userId;
    private String profileImage;

    public long getUserKey() {
        return this.userKey;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getProfileImage() {
        return this.profileImage;
    }

    public ParticipantResponse(final long userKey, final String userId, final String profileImage) {
        this.userKey = userKey;
        this.userId = userId;
        this.profileImage = profileImage;
    }

    public ParticipantResponse() {
    }
}