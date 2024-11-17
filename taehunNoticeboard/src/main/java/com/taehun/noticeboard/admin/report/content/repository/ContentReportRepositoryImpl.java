package com.taehun.noticeboard.admin.report.content.repository;

import com.taehun.noticeboard.account.user.domain.QUser;
import com.taehun.noticeboard.admin.report.content.dto.ContentReportResponse;
import com.taehun.noticeboard.admin.report.content.dto.ReportDataResponse;
import com.taehun.noticeboard.admin.report.content.entity.QContentReport;
import com.taehun.noticeboard.admin.report.content.entity.QReportData;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.data.domain.Pageable;

public class ContentReportRepositoryImpl implements CustomContentReportRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<ContentReportResponse> findAllContentReport(Pageable pageable) {
        QUser targetUser = new QUser("user2");
        return ((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(Projections.constructor(ContentReportResponse.class, new Expression[]{QContentReport.contentReport.contentReportId, QUser.user.id, QContentReport.contentReport.reportTime, QContentReport.contentReport.content, QContentReport.contentReport.isAction, QContentReport.contentReport.reportType, Projections.constructor(ReportDataResponse.class, new Expression[]{QReportData.reportData.reportDataId, targetUser.id, QReportData.reportData.title, QReportData.reportData.content})})).from(QContentReport.contentReport)).innerJoin(QContentReport.contentReport.reporter, QUser.user)).innerJoin(QContentReport.contentReport.reportData, QReportData.reportData)).innerJoin(QReportData.reportData.target, targetUser)).offset(pageable.getOffset())).limit((long)pageable.getPageSize())).orderBy(QContentReport.contentReport.reportTime.desc())).fetch();
    }

    public ContentReportRepositoryImpl(final JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
