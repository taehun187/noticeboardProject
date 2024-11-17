package com.taehun.noticeboard.meeting.reservation.repository;

import com.taehun.noticeboard.meeting.reservation.entity.ReservationParticipants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationParticipantsRepository extends JpaRepository<ReservationParticipants, Long> {
}