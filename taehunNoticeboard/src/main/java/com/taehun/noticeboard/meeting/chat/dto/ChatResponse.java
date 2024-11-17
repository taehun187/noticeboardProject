package com.taehun.noticeboard.meeting.chat.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatResponse {
    private long chatId;
    private String sender;
    private String message;
    private String sendTime;
    private String senderImage;

    public static ChatResponse createChatResponse(ChatRequest chatMessage) {
        return builder().sender(chatMessage.getSender()).sendTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).message(chatMessage.getMessage()).senderImage(chatMessage.getSenderImage()).build();
    }

    public static ChatResponseBuilder builder() {
        return new ChatResponseBuilder();
    }

    public ChatResponse() {
    }

    public ChatResponse(final long chatId, final String sender, final String message, final String sendTime, final String senderImage) {
        this.chatId = chatId;
        this.sender = sender;
        this.message = message;
        this.sendTime = sendTime;
        this.senderImage = senderImage;
    }

    public long getChatId() {
        return this.chatId;
    }

    public String getSender() {
        return this.sender;
    }

    public String getMessage() {
        return this.message;
    }

    public String getSendTime() {
        return this.sendTime;
    }

    public String getSenderImage() {
        return this.senderImage;
    }

    public static class ChatResponseBuilder {
        private long chatId;
        private String sender;
        private String message;
        private String sendTime;
        private String senderImage;

        ChatResponseBuilder() {
        }

        public ChatResponseBuilder chatId(final long chatId) {
            this.chatId = chatId;
            return this;
        }

        public ChatResponseBuilder sender(final String sender) {
            this.sender = sender;
            return this;
        }

        public ChatResponseBuilder message(final String message) {
            this.message = message;
            return this;
        }

        public ChatResponseBuilder sendTime(final String sendTime) {
            this.sendTime = sendTime;
            return this;
        }

        public ChatResponseBuilder senderImage(final String senderImage) {
            this.senderImage = senderImage;
            return this;
        }

        public ChatResponse build() {
            return new ChatResponse(this.chatId, this.sender, this.message, this.sendTime, this.senderImage);
        }

        public String toString() {
            return "ChatResponse.ChatResponseBuilder(chatId=" + this.chatId + ", sender=" + this.sender + ", message=" + this.message + ", sendTime=" + this.sendTime + ", senderImage=" + this.senderImage + ")";
        }
    }
}

