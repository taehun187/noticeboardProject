package com.taehun.noticeboard.post.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.util.Date;

public class PostListResponse {
    private long numbers;
    private String title;
    private String writer;
    private String writerImage;
    private boolean writerIsDelete;
    @JsonFormat(
        shape = Shape.STRING,
        pattern = "yyyy-MM-dd",
        timezone = "Asia/Seoul"
    )
    private Date postDate;
    private int likes;
    private int views;
    private long count;

    public static PostListResponseBuilder builder() {
        return new PostListResponseBuilder();
    }

    public long getNumbers() {
        return this.numbers;
    }

    public String getTitle() {
        return this.title;
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

    public int getLikes() {
        return this.likes;
    }

    public int getViews() {
        return this.views;
    }

    public long getCount() {
        return this.count;
    }

    public void setNumbers(final long numbers) {
        this.numbers = numbers;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setWriter(final String writer) {
        this.writer = writer;
    }

    public void setWriterImage(final String writerImage) {
        this.writerImage = writerImage;
    }

    public void setWriterIsDelete(final boolean writerIsDelete) {
        this.writerIsDelete = writerIsDelete;
    }

    @JsonFormat(
        shape = Shape.STRING,
        pattern = "yyyy-MM-dd",
        timezone = "Asia/Seoul"
    )
    public void setPostDate(final Date postDate) {
        this.postDate = postDate;
    }

    public void setLikes(final int likes) {
        this.likes = likes;
    }

    public void setViews(final int views) {
        this.views = views;
    }

    public void setCount(final long count) {
        this.count = count;
    }

    public PostListResponse(final long numbers, final String title, final String writer, final String writerImage, final boolean writerIsDelete, final Date postDate, final int likes, final int views, final long count) {
        this.numbers = numbers;
        this.title = title;
        this.writer = writer;
        this.writerImage = writerImage;
        this.writerIsDelete = writerIsDelete;
        this.postDate = postDate;
        this.likes = likes;
        this.views = views;
        this.count = count;
    }

    public PostListResponse() {
    }

    public static class PostListResponseBuilder {
        private long numbers;
        private String title;
        private String writer;
        private String writerImage;
        private boolean writerIsDelete;
        private Date postDate;
        private int likes;
        private int views;
        private long count;

        PostListResponseBuilder() {
        }

        public PostListResponseBuilder numbers(final long numbers) {
            this.numbers = numbers;
            return this;
        }

        public PostListResponseBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public PostListResponseBuilder writer(final String writer) {
            this.writer = writer;
            return this;
        }

        public PostListResponseBuilder writerImage(final String writerImage) {
            this.writerImage = writerImage;
            return this;
        }

        public PostListResponseBuilder writerIsDelete(final boolean writerIsDelete) {
            this.writerIsDelete = writerIsDelete;
            return this;
        }

        @JsonFormat(
            shape = Shape.STRING,
            pattern = "yyyy-MM-dd",
            timezone = "Asia/Seoul"
        )
        public PostListResponseBuilder postDate(final Date postDate) {
            this.postDate = postDate;
            return this;
        }

        public PostListResponseBuilder likes(final int likes) {
            this.likes = likes;
            return this;
        }

        public PostListResponseBuilder views(final int views) {
            this.views = views;
            return this;
        }

        public PostListResponseBuilder count(final long count) {
            this.count = count;
            return this;
        }

        public PostListResponse build() {
            return new PostListResponse(this.numbers, this.title, this.writer, this.writerImage, this.writerIsDelete, this.postDate, this.likes, this.views, this.count);
        }

        public String toString() {
            return "PostListResponse.PostListResponseBuilder(numbers=" + this.numbers + ", title=" + this.title + ", writer=" + this.writer + ", writerImage=" + this.writerImage + ", writerIsDelete=" + this.writerIsDelete + ", postDate=" + this.postDate + ", likes=" + this.likes + ", views=" + this.views + ", count=" + this.count + ")";
        }
    }
}