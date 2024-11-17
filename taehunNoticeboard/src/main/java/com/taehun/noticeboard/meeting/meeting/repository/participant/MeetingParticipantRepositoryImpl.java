package com.taehun.noticeboard.meeting.meeting.repository.participant;

import com.taehun.noticeboard.account.user.domain.QUser;
import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.meeting.meeting.domain.MeetingParticipant;
import com.taehun.noticeboard.meeting.meeting.domain.QMeeting;
import com.taehun.noticeboard.meeting.meeting.domain.QMeetingParticipant;
import com.taehun.noticeboard.meeting.meeting.dto.MeetingResponse;
import com.taehun.noticeboard.meeting.meeting.dto.ParticipantResponse;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public class MeetingParticipantRepositoryImpl implements CustomMeetingParticipantRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Optional<MeetingParticipant> findMeetingParticipantByMeetingIdAndUser(long meetingId, User userData) {
        MeetingParticipant result = (MeetingParticipant)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(QMeetingParticipant.meetingParticipant).from(QMeetingParticipant.meetingParticipant)).innerJoin(QMeetingParticipant.meetingParticipant.meetingList, QMeeting.meeting)).on(QMeeting.meeting.meetingId.eq(meetingId))).innerJoin(QMeetingParticipant.meetingParticipant.userList, QUser.user)).on(QUser.user.eq(userData))).fetchOne();
        return Optional.ofNullable(result);
    }

    public List<MeetingResponse> findAllMeetingByParticipant(Pageable pageable, String userId) {
        QMeeting meeting1 = new QMeeting("meeting1");
        return ((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(Projections.constructor(MeetingResponse.class, new Expression[]{QMeeting.meeting.meetingId, QMeeting.meeting.locateX, QMeeting.meeting.locateY, QMeeting.meeting.address, QMeeting.meeting.meetingImage, QMeeting.meeting.detailAddress, QMeeting.meeting.description, QMeeting.meeting.title, QMeeting.meeting.maxParticipants, JPAExpressions.select(ExpressionUtils.count(QMeetingParticipant.meetingParticipant)).from(new EntityPath[]{QMeetingParticipant.meetingParticipant}).innerJoin(QMeetingParticipant.meetingParticipant.meetingList, meeting1).on(new Predicate[]{meeting1.meetingId.eq(QMeeting.meeting.meetingId)})})).from(QMeetingParticipant.meetingParticipant)).innerJoin(QMeetingParticipant.meetingParticipant.userList, QUser.user)).on(QUser.user.id.eq(userId))).innerJoin(QMeetingParticipant.meetingParticipant.meetingList, QMeeting.meeting)).orderBy(QMeeting.meeting.meetingId.desc())).where(QMeeting.meeting.isDelete.eq(false))).limit((long)pageable.getPageSize())).offset(pageable.getOffset())).fetch();
    }

    public long countAllMeetingByParticipant(String userId) {
        return (Long)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(QMeeting.meeting.count()).from(QMeetingParticipant.meetingParticipant)).innerJoin(QMeetingParticipant.meetingParticipant.userList, QUser.user)).on(QUser.user.id.eq(userId))).innerJoin(QMeetingParticipant.meetingParticipant.meetingList, QMeeting.meeting)).fetchOne();
    }

    public List<ParticipantResponse> findParticipantsByMeetingId(long meetingId) {
        return ((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(Projections.constructor(ParticipantResponse.class, new Expression[]{QUser.user.userKey, QUser.user.id, QUser.user.profileImage})).from(QMeetingParticipant.meetingParticipant)).innerJoin(QMeetingParticipant.meetingParticipant.meetingList, QMeeting.meeting)).on(QMeeting.meeting.meetingId.eq(meetingId))).innerJoin(QMeetingParticipant.meetingParticipant.userList, QUser.user)).fetch();
    }

    public long deleteParticipantsByMeetingIdAndUserId(long meetingId, String userId) {
        long id = (Long)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(QMeetingParticipant.meetingParticipant.meetingParticipantId).from(QMeetingParticipant.meetingParticipant)).innerJoin(QMeetingParticipant.meetingParticipant.userList, QUser.user)).on(QUser.user.id.eq(userId))).innerJoin(QMeetingParticipant.meetingParticipant.meetingList, QMeeting.meeting)).on(QMeeting.meeting.meetingId.eq(meetingId))).fetchOne();
        return this.jpaQueryFactory.delete(QMeetingParticipant.meetingParticipant).where(new Predicate[]{QMeetingParticipant.meetingParticipant.meetingParticipantId.eq(id)}).execute();
    }

    public MeetingParticipantRepositoryImpl(final JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
}