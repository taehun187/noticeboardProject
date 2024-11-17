package com.taehun.noticeboard.admin.visitant.repository;

import com.taehun.noticeboard.admin.visitant.domain.QVisitant;
import com.taehun.noticeboard.admin.visitant.dto.VisitantResponse;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;

public class VisitantRepositoryImpl implements CustomVisitantRepository {
    private final JPAQueryFactory queryFactory;

    public List<VisitantResponse> findVisitantCountByVisitDate() {
        return ((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.queryFactory.select(Projections.constructor(VisitantResponse.class, new Expression[]{QVisitant.visitant.count(), QVisitant.visitant.visitDate})).from(QVisitant.visitant)).groupBy(QVisitant.visitant.visitDate)).orderBy(QVisitant.visitant.visitDate.asc())).limit(20L)).fetch();
    }

    public VisitantRepositoryImpl(final JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
