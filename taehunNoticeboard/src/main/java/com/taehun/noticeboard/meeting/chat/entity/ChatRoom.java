package com.taehun.noticeboard.meeting.chat.entity;

import com.taehun.noticeboard.common.config.BooleanConverter;
import com.taehun.noticeboard.meeting.meeting.domain.Meeting;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class ChatRoom {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long chatId;
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    private Meeting meetingId;
    @Column(
        nullable = false
    )
    @Convert(
        converter = BooleanConverter.class
    )
    private boolean isDelete;
    @CreationTimestamp
    private LocalDate createDate;

    public static ChatRoom createChatRoom(Meeting meeting) {
        return builder().meetingId(meeting).build();
    }

    public static ChatRoomBuilder builder() {
        return new ChatRoomBuilder();
    }

    public ChatRoom(final long chatId, final Meeting meetingId, final boolean isDelete, final LocalDate createDate) {
        this.chatId = chatId;
        this.meetingId = meetingId;
        this.isDelete = isDelete;
        this.createDate = createDate;
    }

    public ChatRoom() {
    }

    public long getChatId() {
        return this.chatId;
    }

    public Meeting getMeetingId() {
        return this.meetingId;
    }

    public boolean isDelete() {
        return this.isDelete;
    }

    public LocalDate getCreateDate() {
        return this.createDate;
    }

    public static class ChatRoomBuilder {
        private long chatId;
        private Meeting meetingId;
        private boolean isDelete;
        private LocalDate createDate;

        ChatRoomBuilder() {
        }

        public ChatRoomBuilder chatId(final long chatId) {
            this.chatId = chatId;
            return this;
        }

        public ChatRoomBuilder meetingId(final Meeting meetingId) {
            this.meetingId = meetingId;
            return this;
        }

        public ChatRoomBuilder isDelete(final boolean isDelete) {
            this.isDelete = isDelete;
            return this;
        }

        public ChatRoomBuilder createDate(final LocalDate createDate) {
            this.createDate = createDate;
            return this;
        }

        public ChatRoom build() {
            return new ChatRoom(this.chatId, this.meetingId, this.isDelete, this.createDate);
        }

        public String toString() {
            return "ChatRoom.ChatRoomBuilder(chatId=" + this.chatId + ", meetingId=" + this.meetingId + ", isDelete=" + this.isDelete + ", createDate=" + this.createDate + ")";
        }
    }
}