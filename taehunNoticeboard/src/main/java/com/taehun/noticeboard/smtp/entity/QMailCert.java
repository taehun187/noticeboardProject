package com.taehun.noticeboard.smtp.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

public class QMailCert extends EntityPathBase<MailCert> {
    private static final long serialVersionUID = -923676833L;
    public static final QMailCert mailCert = new QMailCert("mailCert");
    public final StringPath id = this.createString("id");
    public final NumberPath<Long> mailCertId = this.createNumber("mailCertId", Long.class);
    public final StringPath verificationCode = this.createString("verificationCode");

    public QMailCert(String variable) {
        super(MailCert.class, PathMetadataFactory.forVariable(variable));
    }

    public QMailCert(Path<? extends MailCert> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMailCert(PathMetadata metadata) {
        super(MailCert.class, metadata);
    }
}