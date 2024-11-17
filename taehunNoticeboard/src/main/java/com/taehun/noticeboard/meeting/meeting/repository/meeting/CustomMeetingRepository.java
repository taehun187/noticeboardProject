package com.taehun.noticeboard.meeting.meeting.repository.meeting;

import com.taehun.noticeboard.meeting.meeting.domain.Meeting;
import com.taehun.noticeboard.meeting.meeting.dto.MeetingMemberResponse;
import com.taehun.noticeboard.meeting.meeting.dto.MeetingResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface CustomMeetingRepository {
    List<MeetingResponse> findAllMeeting(Pageable pageable);

    Optional<Meeting> findMeetingByMeetingIdAndMeetingOwner(long meetingId, String userId);

    Optional<Meeting> findMeetingAndChatById(long meetingId);

    Optional<MeetingResponse> findMeetingByMeetingId(long meetingId);

    long countAllMeeting();

    List<MeetingResponse> findMeetingByTitleOrAddress(Pageable pageable, String data);

    long countMeetingByTitleOrAddress(String data);

    MeetingMemberResponse findMeetingOwnerByMeetingId(long meetingId);

    Optional<MeetingResponse> findMeetingByMeetingIdAndUser(long meetingId, String userPk);
}
