package com.taehun.noticeboard.post.post.dto;

import com.taehun.noticeboard.post.attachment.dto.AttachmentResponse;
import com.taehun.noticeboard.post.post.domain.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostResponse {
    private long postId;
    private String title;
    private String content;
    private int views;
    private int likes;
    private boolean isPrivate;
    private boolean isBlockComment;
    private String writer;
    private String writerImage;
    private boolean writerIsDelete;
    @JsonFormat(
        shape = Shape.STRING,
        pattern = "yyyy-MM-dd HH:MM:SS",
        timezone = "Asia/Seoul"
    )
    private Date postDate;
    List<String> tags;
    List<AttachmentResponse> attachment;

    public static PostResponse createPostResponse(Post post) {
        return builder().postId(post.getPostId()).title(post.getTitle()).content(post.getContent()).views(post.getViews()).likes(post.getLikes()).isPrivate(post.isPrivate()).isBlockComment(post.isBlockComment()).writer(post.getWriter().getId()).writerImage(post.getWriter().getProfileImage()).writerIsDelete(post.getWriter().isDelete()).postDate(post.getPostDate()).build();
    }

    public void addTagData(List<String> tags) {
        this.tags = tags;
    }

    private static List<String> $default$tags() {
        return new ArrayList();
    }

    private static List<AttachmentResponse> $default$attachment() {
        return new ArrayList();
    }

    PostResponse(final long postId, final String title, final String content, final int views, final int likes, final boolean isPrivate, final boolean isBlockComment, final String writer, final String writerImage, final boolean writerIsDelete, final Date postDate, final List<String> tags, final List<AttachmentResponse> attachment) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.views = views;
        this.likes = likes;
        this.isPrivate = isPrivate;
        this.isBlockComment = isBlockComment;
        this.writer = writer;
        this.writerImage = writerImage;
        this.writerIsDelete = writerIsDelete;
        this.postDate = postDate;
        this.tags = tags;
        this.attachment = attachment;
    }

    public static PostResponseBuilder builder() {
        return new PostResponseBuilder();
    }

    public long getPostId() {
        return this.postId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public int getViews() {
        return this.views;
    }

    public int getLikes() {
        return this.likes;
    }

    public boolean isPrivate() {
        return this.isPrivate;
    }

    public boolean isBlockComment() {
        return this.isBlockComment;
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

    public Date getPostDate() {
        return this.postDate;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public List<AttachmentResponse> getAttachment() {
        return this.attachment;
    }

    public void setAttachment(final List<AttachmentResponse> attachment) {
        this.attachment = attachment;
    }

    public static class PostResponseBuilder {
        private long postId;
        private String title;
        private String content;
        private int views;
        private int likes;
        private boolean isPrivate;
        private boolean isBlockComment;
        private String writer;
        private String writerImage;
        private boolean writerIsDelete;
        private Date postDate;
        private boolean tags$set;
        private List<String> tags$value;
        private boolean attachment$set;
        private List<AttachmentResponse> attachment$value;

        PostResponseBuilder() {
        }

        public PostResponseBuilder postId(final long postId) {
            this.postId = postId;
            return this;
        }

        public PostResponseBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public PostResponseBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public PostResponseBuilder views(final int views) {
            this.views = views;
            return this;
        }

        public PostResponseBuilder likes(final int likes) {
            this.likes = likes;
            return this;
        }

        public PostResponseBuilder isPrivate(final boolean isPrivate) {
            this.isPrivate = isPrivate;
            return this;
        }

        public PostResponseBuilder isBlockComment(final boolean isBlockComment) {
            this.isBlockComment = isBlockComment;
            return this;
        }

        public PostResponseBuilder writer(final String writer) {
            this.writer = writer;
            return this;
        }

        public PostResponseBuilder writerImage(final String writerImage) {
            this.writerImage = writerImage;
            return this;
        }

        public PostResponseBuilder writerIsDelete(final boolean writerIsDelete) {
            this.writerIsDelete = writerIsDelete;
            return this;
        }

        @JsonFormat(
            shape = Shape.STRING,
            pattern = "yyyy-MM-dd HH:MM:SS",
            timezone = "Asia/Seoul"
        )
        public PostResponseBuilder postDate(final Date postDate) {
            this.postDate = postDate;
            return this;
        }

        public PostResponseBuilder tags(final List<String> tags) {
            this.tags$value = tags;
            this.tags$set = true;
            return this;
        }

        public PostResponseBuilder attachment(final List<AttachmentResponse> attachment) {
            this.attachment$value = attachment;
            this.attachment$set = true;
            return this;
        }

        public PostResponse build() {
            List<String> tags$value = this.tags$value;
            if (!this.tags$set) {
                tags$value = PostResponse.$default$tags();
            }

            List<AttachmentResponse> attachment$value = this.attachment$value;
            if (!this.attachment$set) {
                attachment$value = PostResponse.$default$attachment();
            }

            return new PostResponse(this.postId, this.title, this.content, this.views, this.likes, this.isPrivate, this.isBlockComment, this.writer, this.writerImage, this.writerIsDelete, this.postDate, tags$value, attachment$value);
        }

        public String toString() {
            return "PostResponse.PostResponseBuilder(postId=" + this.postId + ", title=" + this.title + ", content=" + this.content + ", views=" + this.views + ", likes=" + this.likes + ", isPrivate=" + this.isPrivate + ", isBlockComment=" + this.isBlockComment + ", writer=" + this.writer + ", writerImage=" + this.writerImage + ", writerIsDelete=" + this.writerIsDelete + ", postDate=" + this.postDate + ", tags$value=" + this.tags$value + ", attachment$value=" + this.attachment$value + ")";
        }
    }
}