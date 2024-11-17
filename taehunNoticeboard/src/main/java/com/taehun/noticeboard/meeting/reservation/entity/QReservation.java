package com.taehun.noticeboard.meeting.reservation.entity;

import com.taehun.noticeboard.meeting.meeting.domain.QMeeting;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;
import java.time.LocalDateTime;

public class QReservation extends EntityPathBase<Reservation> {
    private static final long serialVersionUID = 287164423L;
    private static final PathInits INITS;
    public static final QReservation reservation;
    public final StringPath address;
    public final DateTimePath<LocalDateTime> date;
    public final StringPath description;
    public final StringPath detailAddress;
    public final StringPath locateX;
    public final StringPath locateY;
    public final NumberPath<Integer> maxParticipants;
    public final QMeeting meetings;
    public final ListPath<ReservationParticipants, QReservationParticipants> participates;
    public final NumberPath<Long> reservationId;

    public QReservation(String variable) {
        this(Reservation.class, PathMetadataFactory.forVariable(variable), INITS);
    }

    public QReservation(Path<? extends Reservation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservation(PathMetadata metadata, PathInits inits) {
        this(Reservation.class, metadata, inits);
    }

    public QReservation(Class<? extends Reservation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = this.createString("address");
        this.date = this.createDateTime("date", LocalDateTime.class);
        this.description = this.createString("description");
        this.detailAddress = this.createString("detailAddress");
        this.locateX = this.createString("locateX");
        this.locateY = this.createString("locateY");
        this.maxParticipants = this.createNumber("maxParticipants", Integer.class);
        this.participates = this.createList("participates", ReservationParticipants.class, QReservationParticipants.class, PathInits.DIRECT2);
        this.reservationId = this.createNumber("reservationId", Long.class);
        this.meetings = inits.isInitialized("meetings") ? new QMeeting(this.forProperty("meetings"), inits.get("meetings")) : null;
    }

    static {
        INITS = PathInits.DIRECT2;
        reservation = new QReservation("reservation");
    }
}