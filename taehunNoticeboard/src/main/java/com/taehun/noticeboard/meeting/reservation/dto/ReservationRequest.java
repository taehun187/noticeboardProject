package com.taehun.noticeboard.meeting.reservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;

public class ReservationRequest {
    private String description;
    @JsonFormat(
        shape = Shape.STRING,
        pattern = "yyyy-MM-dd'T'HH:mm",
        timezone = "Asia/Seoul"
    )
    private LocalDateTime date;
    private String address;
    private String detailAddress;
    private String locateX;
    private String locateY;
    private int maxParticipants;

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public String getAddress() {
        return this.address;
    }

    public String getDetailAddress() {
        return this.detailAddress;
    }

    public String getLocateX() {
        return this.locateX;
    }

    public String getLocateY() {
        return this.locateY;
    }

    public int getMaxParticipants() {
        return this.maxParticipants;
    }

    public ReservationRequest(final String description, final LocalDateTime date, final String address, final String detailAddress, final String locateX, final String locateY, final int maxParticipants) {
        this.description = description;
        this.date = date;
        this.address = address;
        this.detailAddress = detailAddress;
        this.locateX = locateX;
        this.locateY = locateY;
        this.maxParticipants = maxParticipants;
    }

    public ReservationRequest() {
    }
}