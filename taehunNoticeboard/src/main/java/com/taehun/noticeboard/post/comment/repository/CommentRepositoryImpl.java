package com.taehun.noticeboard.post.comment.repository;

import com.taehun.noticeboard.account.user.domain.QUser;
import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.post.comment.domain.Comment;
import com.taehun.noticeboard.post.comment.domain.QComment;
import com.taehun.noticeboard.post.comment.dto.CommentResponse;
import com.taehun.noticeboard.post.post.domain.QPost;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;

public class CommentRepositoryImpl implements CustomCommentRepository {
    private final JPAQueryFactory queryFactory;

    public Optional<Comment> findCommentByCommentIdAndUserId(long commentId, String userId) {
        Comment result = (Comment)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.queryFactory.select(QComment.comment).from(QComment.comment)).innerJoin(QComment.comment.writer, QUser.user)).on(QUser.user.id.eq(userId))).where(QComment.comment.commentId.eq(commentId))).fetchOne();
        return Optional.ofNullable(result);
    }

    public List<CommentResponse> findCommentByPostId(long postId) {
        return ((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.queryFactory.select(Projections.constructor(CommentResponse.class, new Expression[]{QPost.post.postId, QComment.comment.commentId, QComment.comment.content, QComment.comment.postDate, QUser.user.id, QUser.user.profileImage, QUser.user.isDelete})).from(QComment.comment)).innerJoin(QComment.comment.writer, QUser.user)).innerJoin(QComment.comment.post, QPost.post)).on(QPost.post.postId.eq(postId))).where(QComment.comment.isDelete.eq(false))).fetch();
    }

    public List<CommentResponse> findCommentByCommentPostWithoutMe(User userData) {
        QUser subUser = new QUser("subUser");
        return ((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.queryFactory.select(Projections.constructor(CommentResponse.class, new Expression[]{QPost.post.postId, QComment.comment.commentId, QComment.comment.content, QComment.comment.postDate, QUser.user.id, QUser.user.profileImage, QUser.user.isDelete})).from(QComment.comment)).innerJoin(QComment.comment.writer, QUser.user)).on(QUser.user.ne(userData))).innerJoin(QComment.comment.post, QPost.post)).on(QPost.post.eqAny((SubQueryExpression)JPAExpressions.select(QPost.post).from(new EntityPath[]{QPost.post}).innerJoin(QPost.post.writer, subUser).on(new Predicate[]{subUser.eq(userData)}).where(new Predicate[]{QPost.post.isDelete.eq(false)})))).orderBy(QComment.comment.commentId.desc())).where(QComment.comment.isDelete.eq(false))).limit(10L)).fetch();
    }

    public CommentRepositoryImpl(final JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
