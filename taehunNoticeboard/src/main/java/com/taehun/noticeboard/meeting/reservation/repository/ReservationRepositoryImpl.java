package com.taehun.noticeboard.meeting.reservation.repository;

import com.taehun.noticeboard.account.user.domain.QUser;
import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.meeting.meeting.domain.QMeeting;
import com.taehun.noticeboard.meeting.meeting.dto.ParticipantResponse;
import com.taehun.noticeboard.meeting.reservation.dto.ReservationResponse;
import com.taehun.noticeboard.meeting.reservation.entity.QReservation;
import com.taehun.noticeboard.meeting.reservation.entity.QReservationParticipants;
import com.taehun.noticeboard.meeting.reservation.entity.Reservation;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ReservationRepositoryImpl implements CustomReservationRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<ReservationResponse> findAllReservationByMeetingId(long meetingId) {
        return ((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(Projections.constructor(ReservationResponse.class, new Expression[]{QReservation.reservation.reservationId, QReservation.reservation.date, QReservation.reservation.address, QReservation.reservation.description, QReservation.reservation.detailAddress, QReservation.reservation.locateX, QReservation.reservation.locateY, QReservation.reservation.maxParticipants, QReservationParticipants.reservationParticipants.count()})).from(QReservation.reservation)).innerJoin(QReservation.reservation.meetings, QMeeting.meeting)).on(QMeeting.meeting.meetingId.eq(meetingId))).leftJoin(QReservation.reservation.participates, QReservationParticipants.reservationParticipants)).groupBy(QReservation.reservation.reservationId)).orderBy(QReservation.reservation.date.desc())).fetch();
    }

    public Optional<Reservation> findReservationByIdAndUser(long reservationId, User userData) {
        Reservation result = (Reservation)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(QReservation.reservation).from(QReservationParticipants.reservationParticipants)).innerJoin(QReservationParticipants.reservationParticipants.user, QUser.user)).on(QUser.user.eq(userData))).innerJoin(QReservationParticipants.reservationParticipants.reservation, QReservation.reservation)).on(QReservation.reservation.reservationId.eq(reservationId))).fetchOne();
        return Optional.ofNullable(result);
    }

    public Optional<Reservation> findReservationBeforeExpiry(long reservationId, String userPk) {
        LocalDateTime currentTime = LocalDateTime.now();
        Reservation result = (Reservation)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(QReservation.reservation).from(QReservationParticipants.reservationParticipants)).innerJoin(QReservationParticipants.reservationParticipants.reservation, QReservation.reservation)).on(QReservation.reservation.reservationId.eq(reservationId).and(QReservation.reservation.date.gt(currentTime)))).innerJoin(QReservationParticipants.reservationParticipants.user, QUser.user)).on(QUser.user.id.eq(userPk))).fetchOne();
        return Optional.ofNullable(result);
    }

    public long leaveReservationById(long reservationId, String userPk) {
        long result = (Long)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(QReservationParticipants.reservationParticipants.reservationParticipantsId).from(QReservationParticipants.reservationParticipants)).innerJoin(QReservationParticipants.reservationParticipants.user, QUser.user)).on(QUser.user.id.eq(userPk))).innerJoin(QReservationParticipants.reservationParticipants.reservation, QReservation.reservation)).on(QReservation.reservation.reservationId.eq(reservationId))).fetchOne();
        return this.jpaQueryFactory.delete(QReservationParticipants.reservationParticipants).where(new Predicate[]{QReservationParticipants.reservationParticipants.reservationParticipantsId.eq(result)}).execute();
    }

    public Optional<Reservation> findReservationAndParticipantsById(long reservationId) {
        Reservation result = (Reservation)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(QReservation.reservation).from(QReservation.reservation)).leftJoin(QReservation.reservation.participates)).fetchJoin()).where(QReservation.reservation.reservationId.eq(reservationId))).fetchOne();
        return Optional.ofNullable(result);
    }

    public List<ParticipantResponse> findParticipantsById(long reservationId) {
        return ((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(Projections.constructor(ParticipantResponse.class, new Expression[]{QUser.user.userKey, QUser.user.id, QUser.user.profileImage})).from(QReservationParticipants.reservationParticipants)).innerJoin(QReservationParticipants.reservationParticipants.user, QUser.user)).innerJoin(QReservationParticipants.reservationParticipants.reservation, QReservation.reservation)).on(QReservation.reservation.reservationId.eq(reservationId))).fetch();
    }

    public ReservationRepositoryImpl(final JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
}