package com.taehun.noticeboard.meeting.chat.dto;

import java.time.LocalDate;

public class ChatRoomResponse {
    private long chatId;
    private long meetingId;
    private String meetingTitle;
    private String meetingImage;
    private LocalDate createDate;

    public static ChatRoomResponseBuilder builder() {
        return new ChatRoomResponseBuilder();
    }

    public ChatRoomResponse() {
    }

    public ChatRoomResponse(final long chatId, final long meetingId, final String meetingTitle, final String meetingImage, final LocalDate createDate) {
        this.chatId = chatId;
        this.meetingId = meetingId;
        this.meetingTitle = meetingTitle;
        this.meetingImage = meetingImage;
        this.createDate = createDate;
    }

    public long getChatId() {
        return this.chatId;
    }

    public long getMeetingId() {
        return this.meetingId;
    }

    public String getMeetingTitle() {
        return this.meetingTitle;
    }

    public String getMeetingImage() {
        return this.meetingImage;
    }

    public LocalDate getCreateDate() {
        return this.createDate;
    }

    public static class ChatRoomResponseBuilder {
        private long chatId;
        private long meetingId;
        private String meetingTitle;
        private String meetingImage;
        private LocalDate createDate;

        ChatRoomResponseBuilder() {
        }

        public ChatRoomResponseBuilder chatId(final long chatId) {
            this.chatId = chatId;
            return this;
        }

        public ChatRoomResponseBuilder meetingId(final long meetingId) {
            this.meetingId = meetingId;
            return this;
        }

        public ChatRoomResponseBuilder meetingTitle(final String meetingTitle) {
            this.meetingTitle = meetingTitle;
            return this;
        }

        public ChatRoomResponseBuilder meetingImage(final String meetingImage) {
            this.meetingImage = meetingImage;
            return this;
        }

        public ChatRoomResponseBuilder createDate(final LocalDate createDate) {
            this.createDate = createDate;
            return this;
        }

        public ChatRoomResponse build() {
            return new ChatRoomResponse(this.chatId, this.meetingId, this.meetingTitle, this.meetingImage, this.createDate);
        }

        public String toString() {
            return "ChatRoomResponse.ChatRoomResponseBuilder(chatId=" + this.chatId + ", meetingId=" + this.meetingId + ", meetingTitle=" + this.meetingTitle + ", meetingImage=" + this.meetingImage + ", createDate=" + this.createDate + ")";
        }
    }
}