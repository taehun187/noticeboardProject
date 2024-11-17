package com.taehun.noticeboard.admin.authority.repository;

import com.taehun.noticeboard.account.user.domain.QUser;
import com.taehun.noticeboard.admin.authority.dto.AuthorityResponse;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.data.domain.Pageable;

public class AuthorityRepositoryImpl implements CustomAuthorityRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<AuthorityResponse> findAllAuthorityUser(Pageable pageable) {
        return ((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(Projections.constructor(AuthorityResponse.class, new Expression[]{QUser.user.userKey, QUser.user.id, QUser.user.joinDate, QUser.user.lastLoginDate, QUser.user.suspensionReason, QUser.user.suspensionDate, QUser.user.isSuspension, QUser.user.role})).from(QUser.user)).offset(pageable.getOffset())).limit((long)pageable.getPageSize())).fetch();
    }

    public List<AuthorityResponse> findAllAuthorityUserById(Pageable pageable, String search) {
        return ((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.jpaQueryFactory.select(Projections.constructor(AuthorityResponse.class, new Expression[]{QUser.user.userKey, QUser.user.id, QUser.user.joinDate, QUser.user.lastLoginDate, QUser.user.suspensionReason, QUser.user.suspensionDate, QUser.user.isSuspension, QUser.user.role})).from(QUser.user)).where(QUser.user.id.contains(search))).offset(pageable.getOffset())).limit((long)pageable.getPageSize())).fetch();
    }

    public AuthorityRepositoryImpl(final JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
