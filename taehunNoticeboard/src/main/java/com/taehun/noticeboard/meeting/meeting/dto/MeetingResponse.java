package com.taehun.noticeboard.meeting.meeting.dto;

public class MeetingResponse {
    private long meetingId;
    private double locateX;
    private double locateY;
    private String address;
    private String meetingImage;
    private String detailAddress;
    private String description;
    private String title;
    private int maxParticipants;
    private long nowParticipants;

    public long getMeetingId() {
        return this.meetingId;
    }

    public double getLocateX() {
        return this.locateX;
    }

    public double getLocateY() {
        return this.locateY;
    }

    public String getAddress() {
        return this.address;
    }

    public String getMeetingImage() {
        return this.meetingImage;
    }

    public String getDetailAddress() {
        return this.detailAddress;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTitle() {
        return this.title;
    }

    public int getMaxParticipants() {
        return this.maxParticipants;
    }

    public long getNowParticipants() {
        return this.nowParticipants;
    }

    public MeetingResponse(final long meetingId, final double locateX, final double locateY, final String address, final String meetingImage, final String detailAddress, final String description, final String title, final int maxParticipants, final long nowParticipants) {
        this.meetingId = meetingId;
        this.locateX = locateX;
        this.locateY = locateY;
        this.address = address;
        this.meetingImage = meetingImage;
        this.detailAddress = detailAddress;
        this.description = description;
        this.title = title;
        this.maxParticipants = maxParticipants;
        this.nowParticipants = nowParticipants;
    }

    public MeetingResponse() {
    }
}