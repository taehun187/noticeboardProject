package com.taehun.noticeboard.meeting.meeting.dto;

public class MeetingRequest {
    private double locateX;
    private double locateY;
    private String address;
    private String title;
    private String description;
    private String detailAddress;
    private int maxParticipants;

    public double getLocateX() {
        return this.locateX;
    }

    public double getLocateY() {
        return this.locateY;
    }

    public String getAddress() {
        return this.address;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDetailAddress() {
        return this.detailAddress;
    }

    public int getMaxParticipants() {
        return this.maxParticipants;
    }

    public void setLocateX(final double locateX) {
        this.locateX = locateX;
    }

    public void setLocateY(final double locateY) {
        this.locateY = locateY;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setDetailAddress(final String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public void setMaxParticipants(final int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public MeetingRequest(final double locateX, final double locateY, final String address, final String title, final String description, final String detailAddress, final int maxParticipants) {
        this.locateX = locateX;
        this.locateY = locateY;
        this.address = address;
        this.title = title;
        this.description = description;
        this.detailAddress = detailAddress;
        this.maxParticipants = maxParticipants;
    }

    public MeetingRequest() {
    }
}
