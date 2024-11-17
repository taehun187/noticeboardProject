package com.taehun.noticeboard.meeting.chat.dto;

public class ChatRequest {
    private String sender;
    private String senderImage;
    private String message;
    private long meetingId;
    private MessageType messageType;

    public ChatRequest() {
    }

    public String getSender() {
        return this.sender;
    }

    public String getSenderImage() {
        return this.senderImage;
    }

    public String getMessage() {
        return this.message;
    }

    public long getMeetingId() {
        return this.meetingId;
    }

    public MessageType getMessageType() {
        return this.messageType;
    }

    public void setSender(final String sender) {
        this.sender = sender;
    }

    public void setSenderImage(final String senderImage) {
        this.senderImage = senderImage;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setMeetingId(final long meetingId) {
        this.meetingId = meetingId;
    }

    public void setMessageType(final MessageType messageType) {
        this.messageType = messageType;
    }
}