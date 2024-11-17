package com.taehun.noticeboard.meeting.meeting.domain;

import com.taehun.noticeboard.account.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MeetingParticipant {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long meetingParticipantId;
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    @JoinColumn(
        name = "meetingId"
    )
    private Meeting meetingList;
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    @JoinColumn(
        name = "userId"
    )
    private User userList;

    public static MeetingParticipant createMeetingParticipant(Meeting meeting, User user) {
        MeetingParticipant participant = builder().meetingList(meeting).userList(user).build();
        user.getParticipants().add(participant);
        return participant;
    }

    public static MeetingParticipantBuilder builder() {
        return new MeetingParticipantBuilder();
    }

    public long getMeetingParticipantId() {
        return this.meetingParticipantId;
    }

    public Meeting getMeetingList() {
        return this.meetingList;
    }

    public User getUserList() {
        return this.userList;
    }

    public MeetingParticipant(final long meetingParticipantId, final Meeting meetingList, final User userList) {
        this.meetingParticipantId = meetingParticipantId;
        this.meetingList = meetingList;
        this.userList = userList;
    }

    public MeetingParticipant() {
    }

    public static class MeetingParticipantBuilder {
        private long meetingParticipantId;
        private Meeting meetingList;
        private User userList;

        MeetingParticipantBuilder() {
        }

        public MeetingParticipantBuilder meetingParticipantId(final long meetingParticipantId) {
            this.meetingParticipantId = meetingParticipantId;
            return this;
        }

        public MeetingParticipantBuilder meetingList(final Meeting meetingList) {
            this.meetingList = meetingList;
            return this;
        }

        public MeetingParticipantBuilder userList(final User userList) {
            this.userList = userList;
            return this;
        }

        public MeetingParticipant build() {
            return new MeetingParticipant(this.meetingParticipantId, this.meetingList, this.userList);
        }

        public String toString() {
            return "MeetingParticipant.MeetingParticipantBuilder(meetingParticipantId=" + this.meetingParticipantId + ", meetingList=" + this.meetingList + ", userList=" + this.userList + ")";
        }
    }
}