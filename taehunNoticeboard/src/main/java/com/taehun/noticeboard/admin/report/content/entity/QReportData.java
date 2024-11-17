package com.taehun.noticeboard.admin.report.content.entity;

import com.taehun.noticeboard.account.user.domain.QUser;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

public class QReportData extends EntityPathBase<ReportData> {
    private static final long serialVersionUID = 219909322L;
    private static final PathInits INITS;
    public static final QReportData reportData;
    public final StringPath content;
    public final NumberPath<Long> reportDataId;
    public final QUser target;
    public final StringPath title;

    public QReportData(String variable) {
        this(ReportData.class, PathMetadataFactory.forVariable(variable), INITS);
    }

    public QReportData(Path<? extends ReportData> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReportData(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReportData(PathMetadata metadata, PathInits inits) {
        this(ReportData.class, metadata, inits);
    }

    public QReportData(Class<? extends ReportData> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.content = this.createString("content");
        this.reportDataId = this.createNumber("reportDataId", Long.class);
        this.title = this.createString("title");
        this.target = inits.isInitialized("target") ? new QUser(this.forProperty("target"), inits.get("target")) : null;
    }

    static {
        INITS = PathInits.DIRECT2;
        reportData = new QReportData("reportData");
    }
}
