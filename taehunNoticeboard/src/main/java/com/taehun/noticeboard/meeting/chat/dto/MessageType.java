package com.taehun.noticeboard.meeting.chat.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MessageType {
    ENTER,
    SEND;

    private MessageType() {
    }

    @JsonCreator
    public static MessageType from(String s) {
        return valueOf(s.toUpperCase());
    }
}