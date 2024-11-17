package com.taehun.noticeboard.meeting.reservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;

public class ReservationResponse {
    private long reservationId;
    @JsonFormat(
        shape = Shape.STRING,
        pattern = "yyyy년MM월dd일 HH시MM분",
        timezone = "Asia/Seoul"
    )
    private LocalDateTime date;
    private String address;
    private String description;
    private String detailAddress;
    private String locateX;
    private String locateY;
    private int maxParticipants;
    private long userCount;

    public long getReservationId() {
        return this.reservationId;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public String getAddress() {
        return this.address;
    }

    public String getDescription() {
        return this.description;
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

    public long getUserCount() {
        return this.userCount;
    }

    public ReservationResponse(final long reservationId, final LocalDateTime date, final String address, final String description, final String detailAddress, final String locateX, final String locateY, final int maxParticipants, final long userCount) {
        this.reservationId = reservationId;
        this.date = date;
        this.address = address;
        this.description = description;
        this.detailAddress = detailAddress;
        this.locateX = locateX;
        this.locateY = locateY;
        this.maxParticipants = maxParticipants;
        this.userCount = userCount;
    }

    public ReservationResponse() {
    }
}