package com.taehun.noticeboard.meeting.meeting.repository.participant;

import com.taehun.noticeboard.meeting.meeting.domain.MeetingParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingParticipantRepository extends JpaRepository<MeetingParticipant, Long>, CustomMeetingParticipantRepository {
}