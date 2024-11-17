package com.taehun.noticeboard.meeting.meeting.dto;

import java.util.List;

public class MeetingMemberResponse {
    private long userKey;
    private String userId;
    private String profileImage;
    private List<ParticipantResponse> participantResponses;

    public MeetingMemberResponse(long userKey, String userId, String profileImage) {
        this.userKey = userKey;
        this.userId = userId;
        this.profileImage = profileImage;
    }

    public void setParticipantResponses(List<ParticipantResponse> participantResponses) {
        this.participantResponses = participantResponses;
    }

    public long getUserKey() {
        return this.userKey;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getProfileImage() {
        return this.profileImage;
    }

    public List<ParticipantResponse> getParticipantResponses() {
        return this.participantResponses;
    }

    public MeetingMemberResponse() {
    }
}