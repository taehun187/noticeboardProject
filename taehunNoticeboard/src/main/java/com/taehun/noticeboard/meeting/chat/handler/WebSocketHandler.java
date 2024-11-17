package com.taehun.noticeboard.meeting.chat.handler;

import com.taehun.noticeboard.common.response.message.MeetingMessage;
import com.taehun.noticeboard.meeting.chat.dto.ChatRequest;
import com.taehun.noticeboard.meeting.chat.dto.ChatResponse;
import com.taehun.noticeboard.meeting.chat.dto.MessageType;
import com.taehun.noticeboard.meeting.chat.entity.Chat;
import com.taehun.noticeboard.meeting.chat.repository.ChatRepository;
import com.taehun.noticeboard.meeting.meeting.domain.Meeting;
import com.taehun.noticeboard.meeting.meeting.exception.MeetingException;
import com.taehun.noticeboard.meeting.meeting.repository.meeting.MeetingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private Map<Long, List<WebSocketSession>> sessionList = new HashMap();
    private final MeetingRepository meetingRepository;
    private final ChatRepository chatRepository;

    @Transactional
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ChatRequest chatMessage = (ChatRequest)objectMapper.readValue((String)message.getPayload(), ChatRequest.class);
        long meetingId = chatMessage.getMeetingId();
        if (chatMessage.getMessageType() == MessageType.ENTER) {
            this.joinChatBySession(meetingId, session);
        } else if (chatMessage.getMessageType() == MessageType.SEND) {
            this.sendChatToSameRootId(meetingId, objectMapper, chatMessage);
        }

    }

    private void joinChatBySession(long meetingId, WebSocketSession session) {
        if (!this.sessionList.containsKey(meetingId)) {
            this.sessionList.put(meetingId, new ArrayList());
        }

        List<WebSocketSession> sessions = (List)this.sessionList.get(meetingId);
        if (!sessions.contains(session)) {
            sessions.add(session);
        }

    }

    private void sendChatToSameRootId(long meetingId, ObjectMapper objectMapper, ChatRequest chatMessage) throws IOException {
        List<WebSocketSession> sessions = (List)this.sessionList.get(meetingId);
        ChatResponse chatResponse = ChatResponse.createChatResponse(chatMessage);
        this.saveChatData(meetingId, chatResponse);
        Iterator var7 = sessions.iterator();

        while(var7.hasNext()) {
            WebSocketSession webSocketSession = (WebSocketSession)var7.next();
            String result = objectMapper.writeValueAsString(chatResponse);
            webSocketSession.sendMessage(new TextMessage(result));
        }

    }

    private void saveChatData(long meetingId, ChatResponse chatResponse) {
        Meeting meeting = (Meeting)this.meetingRepository.findMeetingAndChatById(meetingId).orElseThrow(() -> {
            return new MeetingException(MeetingMessage.NOT_FOUND_MEETING);
        });
        Chat chat = Chat.createChat(chatResponse, meeting);
        meeting.getChats().add(chat);
        this.chatRepository.save(chat);
    }

    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        Iterator var3 = this.sessionList.keySet().iterator();

        while(true) {
            while(var3.hasNext()) {
                Long meetingId = (Long)var3.next();
                List<WebSocketSession> webSocketSessions = (List)this.sessionList.get(meetingId);

                for(int i = 0; i < webSocketSessions.size(); ++i) {
                    WebSocketSession socket = (WebSocketSession)webSocketSessions.get(i);
                    if (socket.getId().equals(session.getId())) {
                        webSocketSessions.remove(i);
                        break;
                    }
                }
            }

            return;
        }
    }

    public WebSocketHandler(final MeetingRepository meetingRepository, final ChatRepository chatRepository) {
        this.meetingRepository = meetingRepository;
        this.chatRepository = chatRepository;
    }
}