package com.taehun.noticeboard.post.comment.domain;

import com.taehun.noticeboard.account.user.domain.QUser;
import com.taehun.noticeboard.post.post.domain.QPost;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;
import java.util.Date;

public class QComment extends EntityPathBase<Comment> {
    private static final long serialVersionUID = -165089855L;
    private static final PathInits INITS;
    public static final QComment comment;
    public final NumberPath<Long> commentId;
    public final StringPath content;
    public final BooleanPath isDelete;
    public final QPost post;
    public final DateTimePath<Date> postDate;
    public final QUser writer;

    public QComment(String variable) {
        this(Comment.class, PathMetadataFactory.forVariable(variable), INITS);
    }

    public QComment(Path<? extends Comment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComment(PathMetadata metadata, PathInits inits) {
        this(Comment.class, metadata, inits);
    }

    public QComment(Class<? extends Comment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.commentId = this.createNumber("commentId", Long.class);
        this.content = this.createString("content");
        this.isDelete = this.createBoolean("isDelete");
        this.postDate = this.createDateTime("postDate", Date.class);
        this.post = inits.isInitialized("post") ? new QPost(this.forProperty("post"), inits.get("post")) : null;
        this.writer = inits.isInitialized("writer") ? new QUser(this.forProperty("writer"), inits.get("writer")) : null;
    }

    static {
        INITS = PathInits.DIRECT2;
        comment = new QComment("comment");
    }
}