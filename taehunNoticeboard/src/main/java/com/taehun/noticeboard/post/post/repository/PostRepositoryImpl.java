package com.taehun.noticeboard.post.post.repository;

import com.taehun.noticeboard.account.user.domain.QUser;
import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.post.comment.domain.QComment;
import com.taehun.noticeboard.post.post.domain.Post;
import com.taehun.noticeboard.post.post.domain.QPost;
import com.taehun.noticeboard.post.post.dto.PostListResponse;
import com.taehun.noticeboard.post.tag.domain.QTag;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public class PostRepositoryImpl implements CustomPostRepository {
    private final JPAQueryFactory queryFactory;

    public Optional<User> findRecommendationFromPost(Long postId, String userId) {
        User result = (User)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.queryFactory.select(QUser.user).from(QPost.post)).innerJoin(QPost.post.recommendation, QUser.user)).on(QUser.user.id.eq(userId))).where(QPost.post.postId.eq(postId))).fetchOne();
        return Optional.ofNullable(result);
    }

    public List<PostListResponse> findPostBySearch(Pageable pageable, String content) {
        return ((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.queryFactory.select(Projections.constructor(PostListResponse.class, new Expression[]{QPost.post.postId, QPost.post.title, QUser.user.id, QUser.user.profileImage, QUser.user.isDelete, QPost.post.postDate, QPost.post.likes, QPost.post.views, QComment.comment.count()})).from(QPost.post)).innerJoin(QPost.post.writer, QUser.user)).on(QPost.post.writer.eq(QUser.user))).leftJoin(QPost.post.comment, QComment.comment)).on(QComment.comment.post.eq(QPost.post))).where(QPost.post.content.contains(content).or(QPost.post.title.contains(content)).and(QPost.post.isDelete.eq(false)))).groupBy(QPost.post.postId)).orderBy(QPost.post.postId.desc())).offset(pageable.getOffset())).limit((long)pageable.getPageSize())).fetch();
    }

    public List<PostListResponse> findPostByTag(Pageable pageable, String tagData) {
        return ((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.queryFactory.select(Projections.constructor(PostListResponse.class, new Expression[]{QPost.post.postId, QPost.post.title, QUser.user.id, QUser.user.profileImage, QUser.user.isDelete, QPost.post.postDate, QPost.post.likes, QPost.post.views, QComment.comment.count()})).from(QPost.post)).innerJoin(QPost.post.tag, QTag.tag)).on(QTag.tag.tagData.eq(tagData))).innerJoin(QPost.post.writer, QUser.user)).on(QPost.post.writer.eq(QUser.user))).leftJoin(QPost.post.comment, QComment.comment)).on(QComment.comment.post.eq(QPost.post))).groupBy(QPost.post.postId)).orderBy(QPost.post.postId.desc())).where(QPost.post.isDelete.eq(false))).offset(pageable.getOffset())).limit((long)pageable.getPageSize())).fetch();
    }

    public Optional<Post> findPostByPostId(long postId) {
        Post result = (Post)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.queryFactory.select(QPost.post).from(QPost.post)).join(QPost.post.writer, QUser.user)).fetchJoin()).where(QPost.post.postId.eq(postId))).fetchOne();
        return Optional.ofNullable(result);
    }

    public List<PostListResponse> getPostList(Pageable pageable) {
        return ((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.queryFactory.select(Projections.constructor(PostListResponse.class, new Expression[]{QPost.post.postId, QPost.post.title, QUser.user.id, QUser.user.profileImage, QUser.user.isDelete, QPost.post.postDate, QPost.post.likes, QPost.post.views, QComment.comment.count()})).from(QPost.post)).innerJoin(QPost.post.writer, QUser.user)).on(QPost.post.writer.eq(QUser.user))).leftJoin(QPost.post.comment, QComment.comment)).on(QComment.comment.post.eq(QPost.post))).groupBy(QPost.post.postId)).where(QPost.post.isDelete.eq(false))).orderBy(QPost.post.postId.desc())).offset(pageable.getOffset())).limit((long)pageable.getPageSize())).fetch();
    }

    public long getTotalNumberOfPosts() {
        return (Long)((JPAQuery)((JPAQuery)this.queryFactory.select(QPost.post.count()).from(QPost.post)).where(QPost.post.isDelete.eq(false))).fetchOne();
    }

    public long getTotalNumberOfTagSearchPosts(String tagData) {
        return (Long)((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.queryFactory.select(QPost.post.count()).from(QPost.post)).innerJoin(QPost.post.tag, QTag.tag)).on(QTag.tag.tagData.eq(tagData))).where(QPost.post.isDelete.eq(false))).fetchOne();
    }

    public long getTotalNumberOfSearchPosts(String search) {
        return (Long)((JPAQuery)((JPAQuery)this.queryFactory.select(QPost.post.count()).from(QPost.post)).where(QPost.post.title.contains(search).or(QPost.post.content.contains(search)).and(QPost.post.isDelete.eq(false)))).fetchOne();
    }

    public List<String> findTagsInPostId(long postId) {
        return ((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.queryFactory.select(QTag.tag.tagData).from(QTag.tag)).leftJoin(QPost.post)).on(QPost.post.tag.contains(QTag.tag))).where(QPost.post.postId.eq(postId).and(QPost.post.isDelete.eq(false)))).fetch();
    }

    public void updatePostView(long postId) {
        this.queryFactory.update(QPost.post).set(QPost.post.views, QPost.post.views.add(1)).where(new Predicate[]{QPost.post.postId.eq(postId)}).execute();
    }

    public List<Long> findPostIdByUserId(String userId) {
        return ((JPAQuery)((JPAQuery)((JPAQuery)((JPAQuery)this.queryFactory.select(QPost.post.postId).from(QPost.post)).innerJoin(QPost.post.writer, QUser.user)).on(QUser.user.id.eq(userId))).where(QPost.post.isDelete.eq(false))).fetch();
    }

    public PostRepositoryImpl(final JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}