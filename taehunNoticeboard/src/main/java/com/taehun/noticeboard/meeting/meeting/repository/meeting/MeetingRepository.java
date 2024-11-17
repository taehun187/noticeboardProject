package com.taehun.noticeboard.meeting.meeting.repository.meeting;

import com.taehun.noticeboard.meeting.meeting.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long>, CustomMeetingRepository {
}