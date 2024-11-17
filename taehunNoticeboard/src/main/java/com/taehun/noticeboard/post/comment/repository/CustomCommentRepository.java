package com.taehun.noticeboard.post.comment.repository;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.post.comment.domain.Comment;
import com.taehun.noticeboard.post.comment.dto.CommentResponse;
import java.util.List;
import java.util.Optional;

public interface CustomCommentRepository {
    Optional<Comment> findCommentByCommentIdAndUserId(long commentId, String userId);

    List<CommentResponse> findCommentByPostId(long postId);

    List<CommentResponse> findCommentByCommentPostWithoutMe(User user);
}