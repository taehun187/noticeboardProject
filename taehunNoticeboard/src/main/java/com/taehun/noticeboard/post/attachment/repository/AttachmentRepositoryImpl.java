package com.taehun.noticeboard.post.attachment.repository;

import com.taehun.noticeboard.post.attachment.domain.QAttachment;
import com.taehun.noticeboard.post.attachment.dto.AttachmentResponse;
import com.taehun.noticeboard.post.post.domain.QPost;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;

public class AttachmentRepositoryImpl implements CustomAttachmentRepository {
    private final JPAQueryFactory queryFactory;

    public List<AttachmentResponse> findAttachmentsByPostId(long postId) {
        return ((JPAQuery)((JPAQuery)((JPAQuery)this.queryFactory.select(Projections.constructor(AttachmentResponse.class, new Expression[]{QAttachment.attachment.attachmentId, QAttachment.attachment.realFileName, QAttachment.attachment.s3Url, QAttachment.attachment.fileSize, QAttachment.attachment.uuidFileName})).from(QAttachment.attachment)).innerJoin(QAttachment.attachment.post, QPost.post)).on(QPost.post.postId.eq(postId))).fetch();
    }

    public AttachmentRepositoryImpl(final JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}