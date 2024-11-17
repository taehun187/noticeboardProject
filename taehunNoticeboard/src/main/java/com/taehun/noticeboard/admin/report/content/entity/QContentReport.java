package com.taehun.noticeboard.admin.report.content.entity;

import com.taehun.noticeboard.account.user.domain.QUser;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;
import java.time.LocalDateTime;

public class QContentReport extends EntityPathBase<ContentReport> {
    private static final long serialVersionUID = 569673153L;
    private static final PathInits INITS;
    public static final QContentReport contentReport;
    public final StringPath content;
    public final NumberPath<Long> contentReportId;
    public final BooleanPath isAction;
    public final QReportData reportData;
    public final QUser reporter;
    public final DateTimePath<LocalDateTime> reportTime;
    public final EnumPath<ReportType> reportType;

    public QContentReport(String variable) {
        this(ContentReport.class, PathMetadataFactory.forVariable(variable), INITS);
    }

    public QContentReport(Path<? extends ContentReport> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContentReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContentReport(PathMetadata metadata, PathInits inits) {
        this(ContentReport.class, metadata, inits);
    }

    public QContentReport(Class<? extends ContentReport> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.content = this.createString("content");
        this.contentReportId = this.createNumber("contentReportId", Long.class);
        this.isAction = this.createBoolean("isAction");
        this.reportTime = this.createDateTime("reportTime", LocalDateTime.class);
        this.reportType = this.createEnum("reportType", ReportType.class);
        this.reportData = inits.isInitialized("reportData") ? new QReportData(this.forProperty("reportData"), inits.get("reportData")) : null;
        this.reporter = inits.isInitialized("reporter") ? new QUser(this.forProperty("reporter"), inits.get("reporter")) : null;
    }

    static {
        INITS = PathInits.DIRECT2;
        contentReport = new QContentReport("contentReport");
    }
}
