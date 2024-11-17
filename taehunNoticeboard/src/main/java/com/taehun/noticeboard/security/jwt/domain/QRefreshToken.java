package com.taehun.noticeboard.security.jwt.domain;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

public class QRefreshToken extends EntityPathBase<RefreshToken> {
    private static final long serialVersionUID = -979949628L;
    public static final QRefreshToken refreshToken = new QRefreshToken("refreshToken");
    public final StringPath keyEmail = this.createString("keyEmail");
    public final StringPath token = this.createString("token");
    public final NumberPath<Long> tokenId = this.createNumber("tokenId", Long.class);

    public QRefreshToken(String variable) {
        super(RefreshToken.class, PathMetadataFactory.forVariable(variable));
    }

    public QRefreshToken(Path<? extends RefreshToken> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRefreshToken(PathMetadata metadata) {
        super(RefreshToken.class, metadata);
    }
}