package com.taehun.noticeboard.meeting.reservation.controller;

import com.taehun.noticeboard.meeting.meeting.dto.ParticipantResponse;
import com.taehun.noticeboard.meeting.reservation.dto.ReservationRequest;
import com.taehun.noticeboard.meeting.reservation.dto.ReservationResponse;
import com.taehun.noticeboard.meeting.reservation.service.ReservationService;
import java.util.List;
import javax.security.auth.login.AccountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/meeting"})
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping({"/{meetingId}/reservation"})
    public ResponseEntity createReservation(@RequestBody ReservationRequest request, @CookieValue String accessToken, @PathVariable long meetingId) throws AccountException {
        this.reservationService.createReservation(request, accessToken, meetingId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping({"/reservation/{reservationId}/join"})
    public ResponseEntity joinReservation(@PathVariable long reservationId, @CookieValue String accessToken) throws AccountException {
        this.reservationService.joinReservation(reservationId, accessToken);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping({"/reservation/{reservationId}/leave"})
    public ResponseEntity leaveReservation(@PathVariable long reservationId, @CookieValue String accessToken) throws AccountException {
        this.reservationService.leaveReservation(reservationId, accessToken);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping({"/{meetingId}/reservation"})
    public ResponseEntity findReservation(@PathVariable long meetingId) {
        List<ReservationResponse> result = this.reservationService.findReservationByMeetingId(meetingId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping({"/reservation/{reservationId}"})
    public ResponseEntity findParticipantsById(@PathVariable long reservationId) {
        List<ParticipantResponse> result = this.reservationService.findParticipantsById(reservationId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public ReservationController(final ReservationService reservationService) {
        this.reservationService = reservationService;
    }
}