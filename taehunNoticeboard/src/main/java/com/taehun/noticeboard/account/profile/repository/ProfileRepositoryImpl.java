package com.taehun.noticeboard.account.profile.repository;

import com.taehun.noticeboard.account.profile.dto.StatisticsResponse;
import com.taehun.noticeboard.account.user.domain.QUser;
import com.taehun.noticeboard.post.comment.domain.QComment;
import com.taehun.noticeboard.post.post.domain.QPost;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;

public class ProfileRepositoryImpl implements CustomProfileRepository {
    private final JPAQueryFactory queryFactory;

    public StatisticsResponse getStatisticsOfUser(String userId) {
        long totalPost = (Long)((JPAQuery)((JPAQuery)((JPAQuery)this.queryFactory.select(QPost.post.count()).from(QPost.post)).innerJoin(QPost.post.writer, QUser.user)).on(QUser.user.id.eq(userId))).fetchOne();
        Integer totalView = (Integer)((JPAQuery)((JPAQuery)((JPAQuery)this.queryFactory.select(QPost.post.views.sum()).from(QPost.post)).innerJoin(QPost.post.writer, QUser.user)).on(QUser.user.id.eq(userId))).fetchOne();
        if (totalView == null) {
            totalView = 0;
        }

        long totalComment = (Long)((JPAQuery)((JPAQuery)((JPAQuery)this.queryFactory.select(QComment.comment.count()).from(QComment.comment)).innerJoin(QComment.comment.writer, QUser.user)).on(QUser.user.id.eq(userId))).fetchOne();
        LocalDate joinDate = (LocalDate)((JPAQuery)((JPAQuery)this.queryFactory.select(QUser.user.joinDate).from(QUser.user)).where(QUser.user.id.eq(userId))).fetchOne();
        return StatisticsResponse.builder().totalPost(totalPost).totalPostView((long)totalView).totalComment(totalComment).joinData(joinDate.toString()).build();
    }

    public ProfileRepositoryImpl(final JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
