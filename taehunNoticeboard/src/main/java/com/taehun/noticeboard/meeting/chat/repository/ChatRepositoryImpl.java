package com.taehun.noticeboard.meeting.chat.repository;

import com.taehun.noticeboard.account.user.domain.QUser;
import com.taehun.noticeboard.meeting.chat.dto.ChatResponse;
import com.taehun.noticeboard.meeting.chat.dto.ChatRoomResponse;
import com.taehun.noticeboard.meeting.chat.entity.QChat;
import com.taehun.noticeboard.meeting.chat.entity.QChatRoom;
import com.taehun.noticeboard.meeting.meeting.domain.QMeeting;
import com.taehun.noticeboard.meeting.meeting.domain.QMeetingParticipant;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;

public class ChatRepositoryImpl implements CustomChatRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<ChatResponse> findChatDataByMeetingId(long meetingId) {
        return ((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(Projections.constructor(ChatResponse.class, new Expression[]{QChat.chat.chatId, QChat.chat.sender, QChat.chat.message, QChat.chat.sendTime, QChat.chat.senderImage})).from(QChat.chat)).innerJoin(QChat.chat.meetingId, QMeeting.meeting)).on(QMeeting.meeting.meetingId.eq(meetingId))).fetch();
    }

    public List<ChatRoomResponse> findChatRoomByUserId(String userId) {
        return ((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(Projections.constructor(ChatRoomResponse.class, new Expression[]{QChatRoom.chatRoom.chatId, QMeeting.meeting.meetingId, QMeeting.meeting.title, QMeeting.meeting.meetingImage, QChatRoom.chatRoom.createDate})).from(QMeetingParticipant.meetingParticipant)).innerJoin(QMeetingParticipant.meetingParticipant.userList, QUser.user)).on(QUser.user.id.eq(userId))).innerJoin(QMeetingParticipant.meetingParticipant.meetingList, QMeeting.meeting)).on(QMeeting.meeting.isDelete.eq(false))).innerJoin(QMeeting.meeting.chatRoom, QChatRoom.chatRoom)).fetch();
    }

    public ChatRepositoryImpl(final JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
}