package com.taehun.noticeboard.meeting.chat.entity;

import com.taehun.noticeboard.meeting.meeting.domain.QMeeting;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

public class QChat extends EntityPathBase<Chat> {
    private static final long serialVersionUID = 2107083921L;
    private static final PathInits INITS;
    public static final QChat chat;
    public final NumberPath<Long> chatId;
    public final QMeeting meetingId;
    public final StringPath message;
    public final StringPath sender;
    public final StringPath senderImage;
    public final StringPath sendTime;

    public QChat(String variable) {
        this(Chat.class, PathMetadataFactory.forVariable(variable), INITS);
    }

    public QChat(Path<? extends Chat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChat(PathMetadata metadata, PathInits inits) {
        this(Chat.class, metadata, inits);
    }

    public QChat(Class<? extends Chat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatId = this.createNumber("chatId", Long.class);
        this.message = this.createString("message");
        this.sender = this.createString("sender");
        this.senderImage = this.createString("senderImage");
        this.sendTime = this.createString("sendTime");
        this.meetingId = inits.isInitialized("meetingId") ? new QMeeting(this.forProperty("meetingId"), inits.get("meetingId")) : null;
    }

    static {
        INITS = PathInits.DIRECT2;
        chat = new QChat("chat");
    }
}