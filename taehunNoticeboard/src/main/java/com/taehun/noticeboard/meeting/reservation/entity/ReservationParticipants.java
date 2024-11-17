package com.taehun.noticeboard.meeting.reservation.entity;

import com.taehun.noticeboard.account.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ReservationParticipants {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long reservationParticipantsId;
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    private User user;
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    private Reservation reservation;

    public static ReservationParticipants createReservationParticipants(User user, Reservation reservation) {
        return builder().user(user).reservation(reservation).build();
    }

    public static ReservationParticipantsBuilder builder() {
        return new ReservationParticipantsBuilder();
    }

    public long getReservationParticipantsId() {
        return this.reservationParticipantsId;
    }

    public User getUser() {
        return this.user;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    protected ReservationParticipants(final long reservationParticipantsId, final User user, final Reservation reservation) {
        this.reservationParticipantsId = reservationParticipantsId;
        this.user = user;
        this.reservation = reservation;
    }

    protected ReservationParticipants() {
    }

    public static class ReservationParticipantsBuilder {
        private long reservationParticipantsId;
        private User user;
        private Reservation reservation;

        ReservationParticipantsBuilder() {
        }

        public ReservationParticipantsBuilder reservationParticipantsId(final long reservationParticipantsId) {
            this.reservationParticipantsId = reservationParticipantsId;
            return this;
        }

        public ReservationParticipantsBuilder user(final User user) {
            this.user = user;
            return this;
        }

        public ReservationParticipantsBuilder reservation(final Reservation reservation) {
            this.reservation = reservation;
            return this;
        }

        public ReservationParticipants build() {
            return new ReservationParticipants(this.reservationParticipantsId, this.user, this.reservation);
        }

        public String toString() {
            return "ReservationParticipants.ReservationParticipantsBuilder(reservationParticipantsId=" + this.reservationParticipantsId + ", user=" + this.user + ", reservation=" + this.reservation + ")";
        }
    }
}