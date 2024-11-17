package com.taehun.noticeboard.meeting.chat.entity;

import com.taehun.noticeboard.meeting.meeting.domain.QMeeting;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import java.time.LocalDate;

public class QChatRoom extends EntityPathBase<ChatRoom> {
    private static final long serialVersionUID = -1760308980L;
    private static final PathInits INITS;
    public static final QChatRoom chatRoom;
    public final NumberPath<Long> chatId;
    public final DatePath<LocalDate> createDate;
    public final BooleanPath isDelete;
    public final QMeeting meetingId;

    public QChatRoom(String variable) {
        this(ChatRoom.class, PathMetadataFactory.forVariable(variable), INITS);
    }

    public QChatRoom(Path<? extends ChatRoom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatRoom(PathMetadata metadata, PathInits inits) {
        this(ChatRoom.class, metadata, inits);
    }

    public QChatRoom(Class<? extends ChatRoom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatId = this.createNumber("chatId", Long.class);
        this.createDate = this.createDate("createDate", LocalDate.class);
        this.isDelete = this.createBoolean("isDelete");
        this.meetingId = inits.isInitialized("meetingId") ? new QMeeting(this.forProperty("meetingId"), inits.get("meetingId")) : null;
    }

    static {
        INITS = PathInits.DIRECT2;
        chatRoom = new QChatRoom("chatRoom");
    }
}