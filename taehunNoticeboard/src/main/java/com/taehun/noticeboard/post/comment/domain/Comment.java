package com.taehun.noticeboard.post.comment.domain;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.common.config.BooleanConverter;
import com.taehun.noticeboard.post.comment.dto.CommentRequest;
import com.taehun.noticeboard.post.post.domain.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Comment {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long commentId;
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    @JoinColumn(
        nullable = false
    )
    private Post post;
    @Column(
        nullable = false,
        length = 200
    )
    private String content;
    @Column(
        nullable = false
    )
    @Convert(
        converter = BooleanConverter.class
    )
    private boolean isDelete;
    @JsonFormat(
        shape = Shape.STRING,
        pattern = "yyyy-MM-dd HH:mm:ss",
        timezone = "Asia/Seoul"
    )
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date postDate;
    @OneToOne(
        fetch = FetchType.LAZY
    )
    private User writer;

    public static Comment createComment(CommentRequest commentRequest, User user) {
        return builder().content(commentRequest.getContent()).writer(user).isDelete(false).build();
    }

    public void deleteComment() {
        this.isDelete = true;
    }

    public static CommentBuilder builder() {
        return new CommentBuilder();
    }

    public long getCommentId() {
        return this.commentId;
    }

    public Post getPost() {
        return this.post;
    }

    public String getContent() {
        return this.content;
    }

    public boolean isDelete() {
        return this.isDelete;
    }

    public Date getPostDate() {
        return this.postDate;
    }

    public User getWriter() {
        return this.writer;
    }

    public Comment() {
    }

    public Comment(final long commentId, final Post post, final String content, final boolean isDelete, final Date postDate, final User writer) {
        this.commentId = commentId;
        this.post = post;
        this.content = content;
        this.isDelete = isDelete;
        this.postDate = postDate;
        this.writer = writer;
    }

    public void setPost(final Post post) {
        this.post = post;
    }

    public static class CommentBuilder {
        private long commentId;
        private Post post;
        private String content;
        private boolean isDelete;
        private Date postDate;
        private User writer;

        CommentBuilder() {
        }

        public CommentBuilder commentId(final long commentId) {
            this.commentId = commentId;
            return this;
        }

        public CommentBuilder post(final Post post) {
            this.post = post;
            return this;
        }

        public CommentBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public CommentBuilder isDelete(final boolean isDelete) {
            this.isDelete = isDelete;
            return this;
        }

        @JsonFormat(
            shape = Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "Asia/Seoul"
        )
        public CommentBuilder postDate(final Date postDate) {
            this.postDate = postDate;
            return this;
        }

        public CommentBuilder writer(final User writer) {
            this.writer = writer;
            return this;
        }

        public Comment build() {
            return new Comment(this.commentId, this.post, this.content, this.isDelete, this.postDate, this.writer);
        }

        public String toString() {
            return "Comment.CommentBuilder(commentId=" + this.commentId + ", post=" + this.post + ", content=" + this.content + ", isDelete=" + this.isDelete + ", postDate=" + this.postDate + ", writer=" + this.writer + ")";
        }
    }
}