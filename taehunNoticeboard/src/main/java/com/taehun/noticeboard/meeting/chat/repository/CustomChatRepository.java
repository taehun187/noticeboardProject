package com.taehun.noticeboard.meeting.chat.repository;

import com.taehun.noticeboard.meeting.chat.dto.ChatResponse;
import com.taehun.noticeboard.meeting.chat.dto.ChatRoomResponse;
import java.util.List;

public interface CustomChatRepository {
    List<ChatResponse> findChatDataByMeetingId(long meetingId);

    List<ChatRoomResponse> findChatRoomByUserId(String userId);
}