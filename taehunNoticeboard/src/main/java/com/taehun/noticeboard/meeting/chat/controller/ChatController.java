package com.taehun.noticeboard.meeting.chat.controller;

import com.taehun.noticeboard.meeting.chat.dto.ChatResponse;
import com.taehun.noticeboard.meeting.chat.dto.ChatRoomResponse;
import com.taehun.noticeboard.meeting.chat.service.ChatService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/chat"})
public class ChatController {
    private final ChatService chatService;

    @GetMapping({"/{meetingId}"})
    public ResponseEntity findChatDataById(@PathVariable long meetingId) {
        List<ChatResponse> result = this.chatService.findChatDataByMeetingId(meetingId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping({"/participants"})
    public ResponseEntity findChattingRoomByUserId(@CookieValue String accessToken) {
        List<ChatRoomResponse> result = this.chatService.findChattingRoomByUserId(accessToken);
        return ResponseEntity.ok().body(result);
    }

    public ChatController(final ChatService chatService) {
        this.chatService = chatService;
    }
}