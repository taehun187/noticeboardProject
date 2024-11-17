package com.taehun.noticeboard.post.attachment.domain;

import com.taehun.noticeboard.post.post.domain.QPost;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

public class QAttachment extends EntityPathBase<Attachment> {
    private static final long serialVersionUID = 1503943199L;
    private static final PathInits INITS;
    public static final QAttachment attachment;
    public final NumberPath<Long> attachmentId;
    public final NumberPath<Long> fileSize;
    public final QPost post;
    public final StringPath realFileName;
    public final StringPath s3Url;
    public final StringPath uuidFileName;

    public QAttachment(String variable) {
        this(Attachment.class, PathMetadataFactory.forVariable(variable), INITS);
    }

    public QAttachment(Path<? extends Attachment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttachment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttachment(PathMetadata metadata, PathInits inits) {
        this(Attachment.class, metadata, inits);
    }

    public QAttachment(Class<? extends Attachment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.attachmentId = this.createNumber("attachmentId", Long.class);
        this.fileSize = this.createNumber("fileSize", Long.class);
        this.realFileName = this.createString("realFileName");
        this.s3Url = this.createString("s3Url");
        this.uuidFileName = this.createString("uuidFileName");
        this.post = inits.isInitialized("post") ? new QPost(this.forProperty("post"), inits.get("post")) : null;
    }

    static {
        INITS = PathInits.DIRECT2;
        attachment = new QAttachment("attachment");
    }
}