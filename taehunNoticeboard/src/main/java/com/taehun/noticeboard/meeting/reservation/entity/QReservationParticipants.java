package com.taehun.noticeboard.meeting.reservation.entity;

import com.taehun.noticeboard.account.user.domain.QUser;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;

public class QReservationParticipants extends EntityPathBase<ReservationParticipants> {
    private static final long serialVersionUID = -1005436601L;
    private static final PathInits INITS;
    public static final QReservationParticipants reservationParticipants;
    public final QReservation reservation;
    public final NumberPath<Long> reservationParticipantsId;
    public final QUser user;

    public QReservationParticipants(String variable) {
        this(ReservationParticipants.class, PathMetadataFactory.forVariable(variable), INITS);
    }

    public QReservationParticipants(Path<? extends ReservationParticipants> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservationParticipants(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservationParticipants(PathMetadata metadata, PathInits inits) {
        this(ReservationParticipants.class, metadata, inits);
    }

    public QReservationParticipants(Class<? extends ReservationParticipants> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reservationParticipantsId = this.createNumber("reservationParticipantsId", Long.class);
        this.reservation = inits.isInitialized("reservation") ? new QReservation(this.forProperty("reservation"), inits.get("reservation")) : null;
        this.user = inits.isInitialized("user") ? new QUser(this.forProperty("user"), inits.get("user")) : null;
    }

    static {
        INITS = PathInits.DIRECT2;
        reservationParticipants = new QReservationParticipants("reservationParticipants");
    }
}