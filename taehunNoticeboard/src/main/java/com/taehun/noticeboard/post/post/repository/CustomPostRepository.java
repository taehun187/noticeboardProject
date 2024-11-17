package com.taehun.noticeboard.post.post.repository;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.post.post.domain.Post;
import com.taehun.noticeboard.post.post.dto.PostListResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface CustomPostRepository {
    Optional<User> findRecommendationFromPost(Long postId, String userId);

    List<PostListResponse> findPostBySearch(Pageable pageable, String content);

    List<PostListResponse> findPostByTag(Pageable pageable, String tagData);

    List<PostListResponse> getPostList(Pageable pageable);

    long getTotalNumberOfPosts();

    long getTotalNumberOfTagSearchPosts(String tagData);

    long getTotalNumberOfSearchPosts(String search);

    Optional<Post> findPostByPostId(long postId);

    List<String> findTagsInPostId(long postId);

    void updatePostView(long postId);

    List<Long> findPostIdByUserId(String userId);
}