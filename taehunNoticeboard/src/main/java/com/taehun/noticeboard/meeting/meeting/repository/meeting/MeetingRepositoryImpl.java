package com.taehun.noticeboard.meeting.meeting.repository.meeting;

import com.taehun.noticeboard.account.user.domain.QUser;
import com.taehun.noticeboard.meeting.meeting.domain.Meeting;
import com.taehun.noticeboard.meeting.meeting.domain.QMeeting;
import com.taehun.noticeboard.meeting.meeting.domain.QMeetingParticipant;
import com.taehun.noticeboard.meeting.meeting.dto.MeetingMemberResponse;
import com.taehun.noticeboard.meeting.meeting.dto.MeetingResponse;
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

public class MeetingRepositoryImpl implements CustomMeetingRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<MeetingResponse> findAllMeeting(Pageable pageable) {
        QMeeting meeting1 = new QMeeting("meeting1");
        return ((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(Projections.constructor(MeetingResponse.class, new Expression[]{QMeeting.meeting.meetingId, QMeeting.meeting.locateX, QMeeting.meeting.locateY, QMeeting.meeting.address, QMeeting.meeting.meetingImage, QMeeting.meeting.detailAddress, QMeeting.meeting.description, QMeeting.meeting.title, QMeeting.meeting.maxParticipants, JPAExpressions.select(ExpressionUtils.count(QMeetingParticipant.meetingParticipant)).from(new EntityPath[]{QMeetingParticipant.meetingParticipant}).innerJoin(QMeetingParticipant.meetingParticipant.meetingList, meeting1).on(new Predicate[]{meeting1.meetingId.eq(QMeeting.meeting.meetingId)})})).from(QMeeting.meeting)).orderBy(QMeeting.meeting.meetingId.desc())).where(QMeeting.meeting.isDelete.eq(false))).offset(pageable.getOffset())).limit((long)pageable.getPageSize())).fetch();
    }

    public Optional<Meeting> findMeetingByMeetingIdAndMeetingOwner(long meetingId, String userId) {
        Meeting result = (Meeting)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(QMeeting.meeting).from(QMeeting.meeting)).innerJoin(QMeeting.meeting.meetingOwner, QUser.user)).on(QUser.user.id.eq(userId))).where(QMeeting.meeting.meetingId.eq(meetingId).and(QMeeting.meeting.isDelete.eq(false)))).fetchOne();
        return Optional.ofNullable(result);
    }

    public Optional<Meeting> findMeetingAndChatById(long meetingId) {
        Meeting result = (Meeting)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(QMeeting.meeting).from(QMeeting.meeting)).leftJoin(QMeeting.meeting.chats)).fetchJoin()).where(QMeeting.meeting.meetingId.eq(meetingId).and(QMeeting.meeting.isDelete.eq(false)))).fetchOne();
        return Optional.ofNullable(result);
    }

    public Optional<MeetingResponse> findMeetingByMeetingId(long meetingId) {
        QMeeting meeting1 = new QMeeting("meeting1");
        MeetingResponse result = (MeetingResponse)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(Projections.constructor(MeetingResponse.class, new Expression[]{QMeeting.meeting.meetingId, QMeeting.meeting.locateX, QMeeting.meeting.locateY, QMeeting.meeting.address, QMeeting.meeting.meetingImage, QMeeting.meeting.detailAddress, QMeeting.meeting.description, QMeeting.meeting.title, QMeeting.meeting.maxParticipants, JPAExpressions.select(ExpressionUtils.count(QMeetingParticipant.meetingParticipant)).from(new EntityPath[]{QMeetingParticipant.meetingParticipant}).innerJoin(QMeetingParticipant.meetingParticipant.meetingList, meeting1).on(new Predicate[]{meeting1.meetingId.eq(QMeeting.meeting.meetingId)})})).from(QMeeting.meeting)).where(QMeeting.meeting.meetingId.eq(meetingId).and(QMeeting.meeting.isDelete.eq(false)))).fetchOne();
        return Optional.ofNullable(result);
    }

    public long countAllMeeting() {
        return (Long)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(QMeeting.meeting.count()).from(QMeeting.meeting)).orderBy(QMeeting.meeting.meetingId.desc())).where(QMeeting.meeting.isDelete.eq(false))).fetchOne();
    }

    public List<MeetingResponse> findMeetingByTitleOrAddress(Pageable pageable, String data) {
        QMeeting meeting1 = new QMeeting("meeting1");
        return ((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(Projections.constructor(MeetingResponse.class, new Expression[]{QMeeting.meeting.meetingId, QMeeting.meeting.locateX, QMeeting.meeting.locateY, QMeeting.meeting.address, QMeeting.meeting.meetingImage, QMeeting.meeting.detailAddress, QMeeting.meeting.description, QMeeting.meeting.title, QMeeting.meeting.maxParticipants, JPAExpressions.select(ExpressionUtils.count(QMeetingParticipant.meetingParticipant)).from(new EntityPath[]{QMeetingParticipant.meetingParticipant}).innerJoin(QMeetingParticipant.meetingParticipant.meetingList, meeting1).on(new Predicate[]{meeting1.meetingId.eq(QMeeting.meeting.meetingId)})})).from(QMeeting.meeting)).where(QMeeting.meeting.title.contains(data).or(QMeeting.meeting.address.contains(data)).and(QMeeting.meeting.isDelete.eq(false)))).orderBy(QMeeting.meeting.meetingId.desc())).limit((long)pageable.getPageSize())).offset(pageable.getOffset())).fetch();
    }

    public long countMeetingByTitleOrAddress(String data) {
        return (Long)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(QMeeting.meeting.count()).from(QMeeting.meeting)).where(QMeeting.meeting.title.contains(data).or(QMeeting.meeting.address.contains(data)).and(QMeeting.meeting.isDelete.eq(false)))).fetchOne();
    }

    public MeetingMemberResponse findMeetingOwnerByMeetingId(long meetingId) {
        return (MeetingMemberResponse)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(Projections.constructor(MeetingMemberResponse.class, new Expression[]{QUser.user.userKey, QUser.user.id, QUser.user.profileImage})).from(QMeeting.meeting)).innerJoin(QMeeting.meeting.meetingOwner, QUser.user)).where(QMeeting.meeting.meetingId.eq(meetingId).and(QMeeting.meeting.isDelete.eq(false)))).fetchOne();
    }

    public Optional<MeetingResponse> findMeetingByMeetingIdAndUser(long meetingId, String userPk) {
        QMeeting meeting1 = new QMeeting("meeting1");
        MeetingResponse result = (MeetingResponse)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(Projections.constructor(MeetingResponse.class, new Expression[]{QMeeting.meeting.meetingId, QMeeting.meeting.locateX, QMeeting.meeting.locateY, QMeeting.meeting.address, QMeeting.meeting.meetingImage, QMeeting.meeting.detailAddress, QMeeting.meeting.description, QMeeting.meeting.title, QMeeting.meeting.maxParticipants, JPAExpressions.select(ExpressionUtils.count(QMeetingParticipant.meetingParticipant)).from(new EntityPath[]{QMeetingParticipant.meetingParticipant}).innerJoin(QMeetingParticipant.meetingParticipant.meetingList, meeting1).on(new Predicate[]{meeting1.meetingId.eq(QMeeting.meeting.meetingId)})})).from(QMeeting.meeting)).innerJoin(QMeeting.meeting.meetingOwner, QUser.user)).on(QUser.user.id.eq(userPk))).where(QMeeting.meeting.meetingId.eq(meetingId).and(QMeeting.meeting.isDelete.eq(false)))).fetchOne();
        return Optional.ofNullable(result);
    }

    public MeetingRepositoryImpl(final JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
}