package com.taehun.noticeboard.meeting.meeting.exception;

import com.taehun.noticeboard.common.response.message.MeetingMessage;

public class MeetingException extends RuntimeException {
    public MeetingException(String message) {
        super(message);
    }

    public MeetingException(MeetingMessage message) {
        super(message.getMessage());
    }
}