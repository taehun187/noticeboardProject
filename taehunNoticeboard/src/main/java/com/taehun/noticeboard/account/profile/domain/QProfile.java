package com.taehun.noticeboard.account.profile.domain;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

public class QProfile extends EntityPathBase<Profile> {
    private static final long serialVersionUID = 1969052054L;
    public static final QProfile profile = new QProfile("profile");
    public final NumberPath<Integer> options = this.createNumber("options", Integer.class);
    public final StringPath phone = this.createString("phone");
    public final NumberPath<Long> profileKey = this.createNumber("profileKey", Long.class);

    public QProfile(String variable) {
        super(Profile.class, PathMetadataFactory.forVariable(variable));
    }

    public QProfile(Path<? extends Profile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProfile(PathMetadata metadata) {
        super(Profile.class, metadata);
    }
}