package com.taehun.noticeboard.meeting.reservation.exception;

import com.taehun.noticeboard.common.response.message.MeetingMessage;

public class ReservationException extends RuntimeException {
    public ReservationException(String message) {
        super(message);
    }

    public ReservationException(MeetingMessage message) {
        super(message.getMessage());
    }
}