package com.taehun.noticeboard.meeting.meeting.domain;

import com.taehun.noticeboard.account.user.domain.QUser;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;

public class QMeetingParticipant extends EntityPathBase<MeetingParticipant> {
    private static final long serialVersionUID = -1228890485L;
    private static final PathInits INITS;
    public static final QMeetingParticipant meetingParticipant;
    public final QMeeting meetingList;
    public final NumberPath<Long> meetingParticipantId;
    public final QUser userList;

    public QMeetingParticipant(String variable) {
        this(MeetingParticipant.class, PathMetadataFactory.forVariable(variable), INITS);
    }

    public QMeetingParticipant(Path<? extends MeetingParticipant> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMeetingParticipant(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMeetingParticipant(PathMetadata metadata, PathInits inits) {
        this(MeetingParticipant.class, metadata, inits);
    }

    public QMeetingParticipant(Class<? extends MeetingParticipant> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.meetingParticipantId = this.createNumber("meetingParticipantId", Long.class);
        this.meetingList = inits.isInitialized("meetingList") ? new QMeeting(this.forProperty("meetingList"), inits.get("meetingList")) : null;
        this.userList = inits.isInitialized("userList") ? new QUser(this.forProperty("userList"), inits.get("userList")) : null;
    }

    static {
        INITS = PathInits.DIRECT2;
        meetingParticipant = new QMeetingParticipant("meetingParticipant");
    }
}