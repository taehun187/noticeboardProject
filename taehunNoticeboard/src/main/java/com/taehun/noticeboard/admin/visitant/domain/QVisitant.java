package com.taehun.noticeboard.admin.visitant.domain;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import java.time.LocalDate;

public class QVisitant extends EntityPathBase<Visitant> {
    private static final long serialVersionUID = 1664498372L;
    public static final QVisitant visitant = new QVisitant("visitant");
    public final StringPath userAgent = this.createString("userAgent");
    public final StringPath userIp = this.createString("userIp");
    public final NumberPath<Long> visitantId = this.createNumber("visitantId", Long.class);
    public final DatePath<LocalDate> visitDate = this.createDate("visitDate", LocalDate.class);

    public QVisitant(String variable) {
        super(Visitant.class, PathMetadataFactory.forVariable(variable));
    }

    public QVisitant(Path<? extends Visitant> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVisitant(PathMetadata metadata) {
        super(Visitant.class, metadata);
    }
}
