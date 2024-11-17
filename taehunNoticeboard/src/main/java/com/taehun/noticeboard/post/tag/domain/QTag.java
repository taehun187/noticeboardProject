package com.taehun.noticeboard.post.tag.domain;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

public class QTag extends EntityPathBase<Tag> {
    private static final long serialVersionUID = 1455870561L;
    public static final QTag tag = new QTag("tag");
    public final StringPath tagData = this.createString("tagData");
    public final NumberPath<Long> tagId = this.createNumber("tagId", Long.class);

    public QTag(String variable) {
        super(Tag.class, PathMetadataFactory.forVariable(variable));
    }

    public QTag(Path<? extends Tag> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTag(PathMetadata metadata) {
        super(Tag.class, metadata);
    }
}