package com.taehun.noticeboard.post.comment.exception;

import com.taehun.noticeboard.common.response.message.CommentMessage;

public class CommentException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CommentException(CommentMessage message) {
        super(message.getMessage());
    }

    public CommentException(String message) {
        super(message);
    }
}