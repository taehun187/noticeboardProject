package com.taehun.noticeboard.post.post.dto;

import java.util.Arrays;

public class PostRequest {
    private String[] tags;
    private boolean blockComment;
    private boolean privatePost;
    private String title;
    private String content;
    private long[] deletedFileIds;

    public static PostRequestBuilder builder() {
        return new PostRequestBuilder();
    }

    public String[] getTags() {
        return this.tags;
    }

    public boolean isBlockComment() {
        return this.blockComment;
    }

    public boolean isPrivatePost() {
        return this.privatePost;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public long[] getDeletedFileIds() {
        return this.deletedFileIds;
    }

    public void setTags(final String[] tags) {
        this.tags = tags;
    }

    public void setBlockComment(final boolean blockComment) {
        this.blockComment = blockComment;
    }

    public void setPrivatePost(final boolean privatePost) {
        this.privatePost = privatePost;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public void setDeletedFileIds(final long[] deletedFileIds) {
        this.deletedFileIds = deletedFileIds;
    }

    public PostRequest(final String[] tags, final boolean blockComment, final boolean privatePost, final String title, final String content, final long[] deletedFileIds) {
        this.tags = tags;
        this.blockComment = blockComment;
        this.privatePost = privatePost;
        this.title = title;
        this.content = content;
        this.deletedFileIds = deletedFileIds;
    }

    public PostRequest() {
    }

    public static class PostRequestBuilder {
        private String[] tags;
        private boolean blockComment;
        private boolean privatePost;
        private String title;
        private String content;
        private long[] deletedFileIds;

        PostRequestBuilder() {
        }

        public PostRequestBuilder tags(final String[] tags) {
            this.tags = tags;
            return this;
        }

        public PostRequestBuilder blockComment(final boolean blockComment) {
            this.blockComment = blockComment;
            return this;
        }

        public PostRequestBuilder privatePost(final boolean privatePost) {
            this.privatePost = privatePost;
            return this;
        }

        public PostRequestBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public PostRequestBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public PostRequestBuilder deletedFileIds(final long[] deletedFileIds) {
            this.deletedFileIds = deletedFileIds;
            return this;
        }

        public PostRequest build() {
            return new PostRequest(this.tags, this.blockComment, this.privatePost, this.title, this.content, this.deletedFileIds);
        }

        public String toString() {
            String var10000 = Arrays.deepToString(this.tags);
            return "PostRequest.PostRequestBuilder(tags=" + var10000 + ", blockComment=" + this.blockComment + ", privatePost=" + this.privatePost + ", title=" + this.title + ", content=" + this.content + ", deletedFileIds=" + Arrays.toString(this.deletedFileIds) + ")";
        }
    }
}
