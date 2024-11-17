package com.taehun.noticeboard.meeting.chat.service;

import com.taehun.noticeboard.meeting.chat.dto.ChatResponse;
import com.taehun.noticeboard.meeting.chat.dto.ChatRoomResponse;
import com.taehun.noticeboard.meeting.chat.entity.ChatRoom;
import com.taehun.noticeboard.meeting.chat.repository.ChatRepository;
import com.taehun.noticeboard.meeting.meeting.domain.Meeting;
import com.taehun.noticeboard.security.jwt.support.JwtTokenProvider;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void createChatRoom(Meeting meeting) {
        ChatRoom chatRoom = ChatRoom.createChatRoom(meeting);
        meeting.setChatRoom(chatRoom);
    }

    public List<ChatRoomResponse> findChattingRoomByUserId(String accessToken) {
        String userId = this.jwtTokenProvider.getUserPk(accessToken);
        return this.chatRepository.findChatRoomByUserId(userId);
    }

    public List<ChatResponse> findChatDataByMeetingId(long meetingId) {
        return this.chatRepository.findChatDataByMeetingId(meetingId);
    }

    public ChatService(final ChatRepository chatRepository, final JwtTokenProvider jwtTokenProvider) {
        this.chatRepository = chatRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }
}