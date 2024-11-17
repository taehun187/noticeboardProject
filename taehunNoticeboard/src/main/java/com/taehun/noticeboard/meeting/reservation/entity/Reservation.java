package com.taehun.noticeboard.meeting.reservation.entity;

import com.taehun.noticeboard.meeting.meeting.domain.Meeting;
import com.taehun.noticeboard.meeting.reservation.dto.ReservationRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long reservationId;
    @OneToMany(
        cascade = {CascadeType.PERSIST},
        mappedBy = "reservation"
    )
    private List<ReservationParticipants> participates;
    @ManyToOne
    private Meeting meetings;
    private LocalDateTime date;
    private String address;
    private String description;
    private String detailAddress;
    private String locateX;
    private String locateY;
    private int maxParticipants;

    public static Reservation createReservation(ReservationRequest request) {
        return builder().date(request.getDate()).address(request.getAddress()).detailAddress(request.getDetailAddress()).description(request.getDescription()).locateX(request.getLocateX()).locateY(request.getLocateY()).maxParticipants(request.getMaxParticipants()).build();
    }

    private static List<ReservationParticipants> $default$participates() {
        return new ArrayList();
    }

    public static ReservationBuilder builder() {
        return new ReservationBuilder();
    }

    public long getReservationId() {
        return this.reservationId;
    }

    public List<ReservationParticipants> getParticipates() {
        return this.participates;
    }

    public Meeting getMeetings() {
        return this.meetings;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public String getAddress() {
        return this.address;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDetailAddress() {
        return this.detailAddress;
    }

    public String getLocateX() {
        return this.locateX;
    }

    public String getLocateY() {
        return this.locateY;
    }

    public int getMaxParticipants() {
        return this.maxParticipants;
    }

    protected Reservation(final long reservationId, final List<ReservationParticipants> participates, final Meeting meetings, final LocalDateTime date, final String address, final String description, final String detailAddress, final String locateX, final String locateY, final int maxParticipants) {
        this.reservationId = reservationId;
        this.participates = participates;
        this.meetings = meetings;
        this.date = date;
        this.address = address;
        this.description = description;
        this.detailAddress = detailAddress;
        this.locateX = locateX;
        this.locateY = locateY;
        this.maxParticipants = maxParticipants;
    }

    protected Reservation() {
        this.participates = $default$participates();
    }

    public void setMeetings(final Meeting meetings) {
        this.meetings = meetings;
    }

    public static class ReservationBuilder {
        private long reservationId;
        private boolean participates$set;
        private List<ReservationParticipants> participates$value;
        private Meeting meetings;
        private LocalDateTime date;
        private String address;
        private String description;
        private String detailAddress;
        private String locateX;
        private String locateY;
        private int maxParticipants;

        ReservationBuilder() {
        }

        public ReservationBuilder reservationId(final long reservationId) {
            this.reservationId = reservationId;
            return this;
        }

        public ReservationBuilder participates(final List<ReservationParticipants> participates) {
            this.participates$value = participates;
            this.participates$set = true;
            return this;
        }

        public ReservationBuilder meetings(final Meeting meetings) {
            this.meetings = meetings;
            return this;
        }

        public ReservationBuilder date(final LocalDateTime date) {
            this.date = date;
            return this;
        }

        public ReservationBuilder address(final String address) {
            this.address = address;
            return this;
        }

        public ReservationBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public ReservationBuilder detailAddress(final String detailAddress) {
            this.detailAddress = detailAddress;
            return this;
        }

        public ReservationBuilder locateX(final String locateX) {
            this.locateX = locateX;
            return this;
        }

        public ReservationBuilder locateY(final String locateY) {
            this.locateY = locateY;
            return this;
        }

        public ReservationBuilder maxParticipants(final int maxParticipants) {
            this.maxParticipants = maxParticipants;
            return this;
        }

        public Reservation build() {
            List<ReservationParticipants> participates$value = this.participates$value;
            if (!this.participates$set) {
                participates$value = Reservation.$default$participates();
            }

            return new Reservation(this.reservationId, participates$value, this.meetings, this.date, this.address, this.description, this.detailAddress, this.locateX, this.locateY, this.maxParticipants);
        }

        public String toString() {
            return "Reservation.ReservationBuilder(reservationId=" + this.reservationId + ", participates$value=" + this.participates$value + ", meetings=" + this.meetings + ", date=" + this.date + ", address=" + this.address + ", description=" + this.description + ", detailAddress=" + this.detailAddress + ", locateX=" + this.locateX + ", locateY=" + this.locateY + ", maxParticipants=" + this.maxParticipants + ")";
        }
    }
}