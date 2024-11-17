package com.taehun.noticeboard.meeting.reservation.service;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.account.user.service.LoginService;
import com.taehun.noticeboard.common.response.message.MeetingMessage;
import com.taehun.noticeboard.meeting.meeting.domain.Meeting;
import com.taehun.noticeboard.meeting.meeting.dto.ParticipantResponse;
import com.taehun.noticeboard.meeting.meeting.exception.MeetingException;
import com.taehun.noticeboard.meeting.meeting.repository.meeting.MeetingRepository;
import com.taehun.noticeboard.meeting.reservation.dto.ReservationRequest;
import com.taehun.noticeboard.meeting.reservation.dto.ReservationResponse;
import com.taehun.noticeboard.meeting.reservation.entity.Reservation;
import com.taehun.noticeboard.meeting.reservation.entity.ReservationParticipants;
import com.taehun.noticeboard.meeting.reservation.exception.ReservationException;
import com.taehun.noticeboard.meeting.reservation.repository.ReservationParticipantsRepository;
import com.taehun.noticeboard.meeting.reservation.repository.ReservationRepository;
import com.taehun.noticeboard.security.jwt.support.JwtTokenProvider;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationParticipantsRepository reservationParticipantsRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final LoginService loginService;
    private final MeetingRepository meetingRepository;

    @Transactional
    public void createReservation(ReservationRequest request, String accessToken, long meetingId) {
        User user = this.loginService.findUserByAccessToken(accessToken);
        Meeting meeting = (Meeting)this.meetingRepository.findById(meetingId).orElseThrow(() -> {
            return new MeetingException(MeetingMessage.NOT_FOUND_MEETING);
        });
        if (meeting.getMeetingOwner().getUserKey() != user.getUserKey()) {
            throw new MeetingException(MeetingMessage.ONLY_OWNER_RESERVATION);
        } else {
            Reservation reservation = Reservation.createReservation(request);
            meeting.addReservation(reservation);
        }
    }

    @Transactional
    public void joinReservation(long reservationId, String accessToken) {
        User user = this.loginService.findUserByAccessToken(accessToken);
        Reservation reservation = (Reservation)this.reservationRepository.findReservationAndParticipantsById(reservationId).orElseThrow(() -> {
            return new ReservationException(MeetingMessage.NOT_FOUND_MEETING);
        });
        if (this.reservationRepository.findReservationByIdAndUser(reservationId, user).isPresent()) {
            throw new ReservationException(MeetingMessage.ALREADY_PARTICIPATED_MEETING);
        } else if (reservation.getParticipates().size() >= reservation.getMaxParticipants()) {
            throw new ReservationException(MeetingMessage.FULL_GATHERING);
        } else {
            this.reservationParticipantsRepository.save(ReservationParticipants.createReservationParticipants(user, reservation));
        }
    }

    @Transactional
    public void leaveReservation(long reservationId, String accessToken) {
        String userPk = this.jwtTokenProvider.getUserPk(accessToken);
        this.reservationRepository.findReservationBeforeExpiry(reservationId, userPk).orElseThrow(() -> {
            return new ReservationException(MeetingMessage.CAN_NOT_LEAVE_LAST_PARTICIPATED);
        });
        this.reservationRepository.leaveReservationById(reservationId, userPk);
    }

    public List<ReservationResponse> findReservationByMeetingId(long meetingId) {
        return this.reservationRepository.findAllReservationByMeetingId(meetingId);
    }

    public List<ParticipantResponse> findParticipantsById(long reservationId) {
        return this.reservationRepository.findParticipantsById(reservationId);
    }

    public ReservationService(final ReservationRepository reservationRepository, final ReservationParticipantsRepository reservationParticipantsRepository, final JwtTokenProvider jwtTokenProvider, final LoginService loginService, final MeetingRepository meetingRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationParticipantsRepository = reservationParticipantsRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.loginService = loginService;
        this.meetingRepository = meetingRepository;
    }
}