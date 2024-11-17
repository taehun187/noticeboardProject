package com.taehun.noticeboard.admin.report.bug.entity;

import com.taehun.noticeboard.account.user.domain.QUser;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;
import java.time.LocalDateTime;

public class QBugReport extends EntityPathBase<BugReport> {
    private static final long serialVersionUID = 326427105L;
    private static final PathInits INITS;
    public static final QBugReport bugReport;
    public final NumberPath<Long> bugReportId;
    public final StringPath content;
    public final BooleanPath isSolved;
    public final QUser reporter;
    public final DateTimePath<LocalDateTime> reportTime;

    public QBugReport(String variable) {
        this(BugReport.class, PathMetadataFactory.forVariable(variable), INITS);
    }

    public QBugReport(Path<? extends BugReport> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBugReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBugReport(PathMetadata metadata, PathInits inits) {
        this(BugReport.class, metadata, inits);
    }

    public QBugReport(Class<? extends BugReport> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bugReportId = this.createNumber("bugReportId", Long.class);
        this.content = this.createString("content");
        this.isSolved = this.createBoolean("isSolved");
        this.reportTime = this.createDateTime("reportTime", LocalDateTime.class);
        this.reporter = inits.isInitialized("reporter") ? new QUser(this.forProperty("reporter"), inits.get("reporter")) : null;
    }

    static {
        INITS = PathInits.DIRECT2;
        bugReport = new QBugReport("bugReport");
    }
}
