package com.taehun.noticeboard.meeting.chat.entity;

import com.taehun.noticeboard.meeting.chat.dto.ChatResponse;
import com.taehun.noticeboard.meeting.meeting.domain.Meeting;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Chat {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long chatId;
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    private Meeting meetingId;
    private String message;
    private String sendTime;
    private String sender;
    private String senderImage;

    public static Chat createChat(ChatResponse chatResponse, Meeting meeting) {
        return builder().message(chatResponse.getMessage()).sendTime(chatResponse.getSendTime()).meetingId(meeting).sender(chatResponse.getSender()).senderImage(chatResponse.getSenderImage()).build();
    }

    public static ChatBuilder builder() {
        return new ChatBuilder();
    }

    public Chat(final long chatId, final Meeting meetingId, final String message, final String sendTime, final String sender, final String senderImage) {
        this.chatId = chatId;
        this.meetingId = meetingId;
        this.message = message;
        this.sendTime = sendTime;
        this.sender = sender;
        this.senderImage = senderImage;
    }

    public Chat() {
    }

    public long getChatId() {
        return this.chatId;
    }

    public Meeting getMeetingId() {
        return this.meetingId;
    }

    public String getMessage() {
        return this.message;
    }

    public String getSendTime() {
        return this.sendTime;
    }

    public String getSender() {
        return this.sender;
    }

    public String getSenderImage() {
        return this.senderImage;
    }

    public static class ChatBuilder {
        private long chatId;
        private Meeting meetingId;
        private String message;
        private String sendTime;
        private String sender;
        private String senderImage;

        ChatBuilder() {
        }

        public ChatBuilder chatId(final long chatId) {
            this.chatId = chatId;
            return this;
        }

        public ChatBuilder meetingId(final Meeting meetingId) {
            this.meetingId = meetingId;
            return this;
        }

        public ChatBuilder message(final String message) {
            this.message = message;
            return this;
        }

        public ChatBuilder sendTime(final String sendTime) {
            this.sendTime = sendTime;
            return this;
        }

        public ChatBuilder sender(final String sender) {
            this.sender = sender;
            return this;
        }

        public ChatBuilder senderImage(final String senderImage) {
            this.senderImage = senderImage;
            return this;
        }

        public Chat build() {
            return new Chat(this.chatId, this.meetingId, this.message, this.sendTime, this.sender, this.senderImage);
        }

        public String toString() {
            return "Chat.ChatBuilder(chatId=" + this.chatId + ", meetingId=" + this.meetingId + ", message=" + this.message + ", sendTime=" + this.sendTime + ", sender=" + this.sender + ", senderImage=" + this.senderImage + ")";
        }
    }
}
