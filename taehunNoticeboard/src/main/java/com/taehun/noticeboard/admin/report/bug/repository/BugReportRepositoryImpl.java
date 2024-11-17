package com.taehun.noticeboard.admin.report.bug.repository;

import com.taehun.noticeboard.account.user.domain.QUser;
import com.taehun.noticeboard.admin.report.bug.dto.BugReportResponse;
import com.taehun.noticeboard.admin.report.bug.entity.QBugReport;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.data.domain.Pageable;

public class BugReportRepositoryImpl implements CustomBugReportRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<BugReportResponse> findAllBugReport(Pageable pageable) {
        return ((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(Projections.constructor(BugReportResponse.class, new Expression[]{QBugReport.bugReport.bugReportId, QUser.user.id, QBugReport.bugReport.reportTime, QBugReport.bugReport.content, QBugReport.bugReport.isSolved})).from(QBugReport.bugReport)).innerJoin(QBugReport.bugReport.reporter, QUser.user)).orderBy(QBugReport.bugReport.bugReportId.desc())).limit((long)pageable.getPageSize())).offset(pageable.getOffset())).fetch();
    }

    public BugReportRepositoryImpl(final JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
