package com.taehun.noticeboard.meeting.reservation.repository;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.meeting.meeting.dto.ParticipantResponse;
import com.taehun.noticeboard.meeting.reservation.dto.ReservationResponse;
import com.taehun.noticeboard.meeting.reservation.entity.Reservation;
import java.util.List;
import java.util.Optional;

public interface CustomReservationRepository {
    List<ReservationResponse> findAllReservationByMeetingId(long meetingId);

    Optional<Reservation> findReservationByIdAndUser(long reservationId, User userData);

    Optional<Reservation> findReservationBeforeExpiry(long reservationId, String userPk);

    long leaveReservationById(long reservationId, String userPk);

    Optional<Reservation> findReservationAndParticipantsById(long reservationId);

    List<ParticipantResponse> findParticipantsById(long reservationId);
}