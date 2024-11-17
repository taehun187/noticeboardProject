package com.taehun.noticeboard.post.post.domain;

import com.taehun.noticeboard.account.user.domain.QUser;
import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.post.attachment.domain.Attachment;
import com.taehun.noticeboard.post.attachment.domain.QAttachment;
import com.taehun.noticeboard.post.comment.domain.Comment;
import com.taehun.noticeboard.post.comment.domain.QComment;
import com.taehun.noticeboard.post.tag.domain.QTag;
import com.taehun.noticeboard.post.tag.domain.Tag;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;
import java.util.Date;

public class QPost extends EntityPathBase<Post> {
    private static final long serialVersionUID = -329816999L;
    private static final PathInits INITS;
    public static final QPost post;
    public final ListPath<Attachment, QAttachment> attachments;
    public final ListPath<Comment, QComment> comment;
    public final StringPath content;
    public final BooleanPath isBlockComment;
    public final BooleanPath isDelete;
    public final BooleanPath isPrivate;
    public final NumberPath<Integer> likes;
    public final DateTimePath<Date> postDate;
    public final NumberPath<Long> postId;
    public final ListPath<User, QUser> recommendation;
    public final ListPath<Tag, QTag> tag;
    public final StringPath title;
    public final NumberPath<Integer> views;
    public final QUser writer;

    public QPost(String variable) {
        this(Post.class, PathMetadataFactory.forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.attachments = this.createList("attachments", Attachment.class, QAttachment.class, PathInits.DIRECT2);
        this.comment = this.createList("comment", Comment.class, QComment.class, PathInits.DIRECT2);
        this.content = this.createString("content");
        this.isBlockComment = this.createBoolean("isBlockComment");
        this.isDelete = this.createBoolean("isDelete");
        this.isPrivate = this.createBoolean("isPrivate");
        this.likes = this.createNumber("likes", Integer.class);
        this.postDate = this.createDateTime("postDate", Date.class);
        this.postId = this.createNumber("postId", Long.class);
        this.recommendation = this.createList("recommendation", User.class, QUser.class, PathInits.DIRECT2);
        this.tag = this.createList("tag", Tag.class, QTag.class, PathInits.DIRECT2);
        this.title = this.createString("title");
        this.views = this.createNumber("views", Integer.class);
        this.writer = inits.isInitialized("writer") ? new QUser(this.forProperty("writer"), inits.get("writer")) : null;
    }

    static {
        INITS = PathInits.DIRECT2;
        post = new QPost("post");
    }
}