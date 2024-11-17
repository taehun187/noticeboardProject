package com.taehun.noticeboard.post.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.util.Date;

public class CommentResponse {
    private long postId;
    private long commentId;
    private String content;
    @JsonFormat(
        shape = Shape.STRING,
        pattern = "yyyy-MM-dd HH:MM:SS",
        timezone = "Asia/Seoul"
    )
    private Date postDate;
    private String writer;
    private String writerImage;
    private boolean writerIsDelete;

    public static CommentResponseBuilder builder() {
        return new CommentResponseBuilder();
    }

    public long getPostId() {
        return this.postId;
    }

    public long getCommentId() {
        return this.commentId;
    }

    public String getContent() {
        return this.content;
    }

    public Date getPostDate() {
        return this.postDate;
    }

    public String getWriter() {
        return this.writer;
    }

    public String getWriterImage() {
        return this.writerImage;
    }

    public boolean isWriterIsDelete() {
        return this.writerIsDelete;
    }

    public CommentResponse() {
    }

    public CommentResponse(final long postId, final long commentId, final String content, final Date postDate, final String writer, final String writerImage, final boolean writerIsDelete) {
        this.postId = postId;
        this.commentId = commentId;
        this.content = content;
        this.postDate = postDate;
        this.writer = writer;
        this.writerImage = writerImage;
        this.writerIsDelete = writerIsDelete;
    }

    public static class CommentResponseBuilder {
        private long postId;
        private long commentId;
        private String content;
        private Date postDate;
        private String writer;
        private String writerImage;
        private boolean writerIsDelete;

        CommentResponseBuilder() {
        }

        public CommentResponseBuilder postId(final long postId) {
            this.postId = postId;
            return this;
        }

        public CommentResponseBuilder commentId(final long commentId) {
            this.commentId = commentId;
            return this;
        }

        public CommentResponseBuilder content(final String content) {
            this.content = content;
            return this;
        }

        @JsonFormat(
            shape = Shape.STRING,
            pattern = "yyyy-MM-dd HH:MM:SS",
            timezone = "Asia/Seoul"
        )
        public CommentResponseBuilder postDate(final Date postDate) {
            this.postDate = postDate;
            return this;
        }

        public CommentResponseBuilder writer(final String writer) {
            this.writer = writer;
            return this;
        }

        public CommentResponseBuilder writerImage(final String writerImage) {
            this.writerImage = writerImage;
            return this;
        }

        public CommentResponseBuilder writerIsDelete(final boolean writerIsDelete) {
            this.writerIsDelete = writerIsDelete;
            return this;
        }

        public CommentResponse build() {
            return new CommentResponse(this.postId, this.commentId, this.content, this.postDate, this.writer, this.writerImage, this.writerIsDelete);
        }

        public String toString() {
            return "CommentResponse.CommentResponseBuilder(postId=" + this.postId + ", commentId=" + this.commentId + ", content=" + this.content + ", postDate=" + this.postDate + ", writer=" + this.writer + ", writerImage=" + this.writerImage + ", writerIsDelete=" + this.writerIsDelete + ")";
        }
    }
}