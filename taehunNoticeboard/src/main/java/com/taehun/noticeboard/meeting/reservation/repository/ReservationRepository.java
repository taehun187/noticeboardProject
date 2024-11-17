package com.taehun.noticeboard.meeting.reservation.repository;

import com.taehun.noticeboard.meeting.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, CustomReservationRepository {
}