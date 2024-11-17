package com.taehun.noticeboard.meeting.meeting.repository.participant;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.meeting.meeting.domain.MeetingParticipant;
import com.taehun.noticeboard.meeting.meeting.dto.MeetingResponse;
import com.taehun.noticeboard.meeting.meeting.dto.ParticipantResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface CustomMeetingParticipantRepository {
    long deleteParticipantsByMeetingIdAndUserId(long meetingId, String userId);

    List<ParticipantResponse> findParticipantsByMeetingId(long meetingId);

    Optional<MeetingParticipant> findMeetingParticipantByMeetingIdAndUser(long meetingId, User user);

    List<MeetingResponse> findAllMeetingByParticipant(Pageable pageable, String userId);

    long countAllMeetingByParticipant(String userId);
}