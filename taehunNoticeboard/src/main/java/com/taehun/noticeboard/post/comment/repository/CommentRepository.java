package com.taehun.noticeboard.post.comment.repository;

import com.taehun.noticeboard.post.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CustomCommentRepository {
}