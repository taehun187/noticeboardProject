package com.taehun.noticeboard.meeting.meeting.domain;

import com.taehun.noticeboard.account.user.domain.QUser;
import com.taehun.noticeboard.meeting.chat.entity.Chat;
import com.taehun.noticeboard.meeting.chat.entity.QChat;
import com.taehun.noticeboard.meeting.chat.entity.QChatRoom;
import com.taehun.noticeboard.meeting.reservation.entity.QReservation;
import com.taehun.noticeboard.meeting.reservation.entity.Reservation;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

public class QMeeting extends EntityPathBase<Meeting> {
    private static final long serialVersionUID = -1732185208L;
    private static final PathInits INITS;
    public static final QMeeting meeting;
    public final StringPath address;
    public final QChatRoom chatRoom;
    public final ListPath<Chat, QChat> chats;
    public final StringPath description;
    public final StringPath detailAddress;
    public final BooleanPath isDelete;
    public final NumberPath<Double> locateX;
    public final NumberPath<Double> locateY;
    public final NumberPath<Integer> maxParticipants;
    public final NumberPath<Long> meetingId;
    public final StringPath meetingImage;
    public final QUser meetingOwner;
    public final ListPath<MeetingParticipant, QMeetingParticipant> meetingParticipants;
    public final ListPath<Reservation, QReservation> reservations;
    public final StringPath title;

    public QMeeting(String variable) {
        this(Meeting.class, PathMetadataFactory.forVariable(variable), INITS);
    }

    public QMeeting(Path<? extends Meeting> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMeeting(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMeeting(PathMetadata metadata, PathInits inits) {
        this(Meeting.class, metadata, inits);
    }

    public QMeeting(Class<? extends Meeting> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = this.createString("address");
        this.chats = this.createList("chats", Chat.class, QChat.class, PathInits.DIRECT2);
        this.description = this.createString("description");
        this.detailAddress = this.createString("detailAddress");
        this.isDelete = this.createBoolean("isDelete");
        this.locateX = this.createNumber("locateX", Double.class);
        this.locateY = this.createNumber("locateY", Double.class);
        this.maxParticipants = this.createNumber("maxParticipants", Integer.class);
        this.meetingId = this.createNumber("meetingId", Long.class);
        this.meetingImage = this.createString("meetingImage");
        this.meetingParticipants = this.createList("meetingParticipants", MeetingParticipant.class, QMeetingParticipant.class, PathInits.DIRECT2);
        this.reservations = this.createList("reservations", Reservation.class, QReservation.class, PathInits.DIRECT2);
        this.title = this.createString("title");
        this.chatRoom = inits.isInitialized("chatRoom") ? new QChatRoom(this.forProperty("chatRoom"), inits.get("chatRoom")) : null;
        this.meetingOwner = inits.isInitialized("meetingOwner") ? new QUser(this.forProperty("meetingOwner"), inits.get("meetingOwner")) : null;
    }

    static {
        INITS = PathInits.DIRECT2;
        meeting = new QMeeting("meeting");
    }
}