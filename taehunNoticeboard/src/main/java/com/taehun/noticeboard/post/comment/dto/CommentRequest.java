package com.taehun.noticeboard.post.comment.dto;

public class CommentRequest {
    private String content;
    private long postId;

    // 기본 생성자 (Jackson을 위한 no-args constructor)
    public CommentRequest() {
    }

    // 모든 필드를 포함한 생성자
    CommentRequest(final String content, final long postId) {
        this.content = content;
        this.postId = postId;
    }

    public static CommentRequestBuilder builder() {
        return new CommentRequestBuilder();
    }

    public String getContent() {
        return this.content;
    }

    public long getPostId() {
        return this.postId;
    }

    public static class CommentRequestBuilder {
        private String content;
        private long postId;

        CommentRequestBuilder() {
        }

        public CommentRequestBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public CommentRequestBuilder postId(final long postId) {
            this.postId = postId;
            return this;
        }

        public CommentRequest build() {
            return new CommentRequest(this.content, this.postId);
        }

        public String toString() {
            return "CommentRequest.CommentRequestBuilder(content=" + this.content + ", postId=" + this.postId + ")";
        }
    }
}
