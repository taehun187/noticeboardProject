package com.taehun.noticeboard.post.post.domain;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.common.config.BooleanConverter;
import com.taehun.noticeboard.post.attachment.domain.Attachment;
import com.taehun.noticeboard.post.comment.domain.Comment;
import com.taehun.noticeboard.post.post.dto.PostRequest;
import com.taehun.noticeboard.post.tag.domain.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@SequenceGenerator(
    name = "NUMBERS_SEQUENCE",
    sequenceName = "ID_numbers",
    initialValue = 1,
    allocationSize = 1
)
@JsonIdentityInfo(
    generator = ObjectIdGenerators.IntSequenceGenerator.class,
    property = "id"
)
public class Post {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long postId;
    @Column(
        nullable = false,
        length = 50
    )
    private String title;
    @Column(
        nullable = false
    )
    private int views;
    @Column(
        nullable = false
    )
    private int likes;
    @Column(
        nullable = false
    )
    @Convert(
        converter = BooleanConverter.class
    )
    private boolean isPrivate;
    @Column(
        nullable = false
    )
    @Convert(
        converter = BooleanConverter.class
    )
    private boolean isBlockComment;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonFormat(
        shape = Shape.STRING,
        pattern = "yyyy-MM-dd HH:mm:ss",
        timezone = "Asia/Seoul"
    )
    private Date postDate;
    @Column(
        nullable = false,
        length = 1100
    )
    private String content;
    @Column(
        nullable = false
    )
    @Convert(
        converter = BooleanConverter.class
    )
    private boolean isDelete;
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    private User writer;
    @OneToMany(
        cascade = {CascadeType.PERSIST}
    )
    private List<User> recommendation;
    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = {CascadeType.ALL}
    )
    private List<Tag> tag;
    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = {CascadeType.ALL},
        mappedBy = "post"
    )
    private List<Attachment> attachments;
    @OneToMany(
        mappedBy = "post",
        fetch = FetchType.LAZY,
        cascade = {CascadeType.ALL}
    )
    private List<Comment> comment;

    public static Post createPost(PostRequest postRequest) {
        return builder().content(postRequest.getContent()).title(postRequest.getTitle()).isPrivate(postRequest.isPrivatePost()).isBlockComment(postRequest.isBlockComment()).isDelete(false).build();
    }

    public void updatePost(PostRequest postRequest) {
        this.content = postRequest.getContent();
        this.title = postRequest.getTitle();
        this.isPrivate = postRequest.isPrivatePost();
        this.isBlockComment = postRequest.isBlockComment();
        this.tag.clear();
        this.addTagFromTagList(postRequest.getTags());
    }

    public void updateLike() {
        ++this.likes;
    }

    public void deletePost() {
        this.isDelete = true;
    }

    public void addFreeAttach(Attachment attach) {
        this.attachments.add(attach);
        attach.setPost(this);
    }

    public void addFreeCommit(Comment commit) {
        this.comment.add(commit);
        commit.setPost(this);
    }

    public void addTagFromTagList(String[] tagList) {
        String[] var2 = tagList;
        int var3 = tagList.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String tag = var2[var4];
            Tag tagEntity = Tag.createFreeTag(tag);
            this.tag.add(tagEntity);
        }

    }

    public void addRecommendation(User user) {
        this.recommendation.add(user);
        this.updateLike();
    }

    private static User $default$writer() {
        return new User();
    }

    private static List<User> $default$recommendation() {
        return new ArrayList();
    }

    private static List<Tag> $default$tag() {
        return new ArrayList();
    }

    private static List<Attachment> $default$attachments() {
        return new ArrayList();
    }

    private static List<Comment> $default$comment() {
        return new ArrayList();
    }

    public static PostBuilder builder() {
        return new PostBuilder();
    }

    public long getPostId() {
        return this.postId;
    }

    public String getTitle() {
        return this.title;
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

    public Date getPostDate() {
        return this.postDate;
    }

    public String getContent() {
        return this.content;
    }

    public boolean isDelete() {
        return this.isDelete;
    }

    public User getWriter() {
        return this.writer;
    }

    public List<User> getRecommendation() {
        return this.recommendation;
    }

    public List<Tag> getTag() {
        return this.tag;
    }

    public List<Attachment> getAttachments() {
        return this.attachments;
    }

    public List<Comment> getComment() {
        return this.comment;
    }

    public Post() {
        this.writer = $default$writer();
        this.recommendation = $default$recommendation();
        this.tag = $default$tag();
        this.attachments = $default$attachments();
        this.comment = $default$comment();
    }

    public Post(final long postId, final String title, final int views, final int likes, final boolean isPrivate, final boolean isBlockComment, final Date postDate, final String content, final boolean isDelete, final User writer, final List<User> recommendation, final List<Tag> tag, final List<Attachment> attachments, final List<Comment> comment) {
        this.postId = postId;
        this.title = title;
        this.views = views;
        this.likes = likes;
        this.isPrivate = isPrivate;
        this.isBlockComment = isBlockComment;
        this.postDate = postDate;
        this.content = content;
        this.isDelete = isDelete;
        this.writer = writer;
        this.recommendation = recommendation;
        this.tag = tag;
        this.attachments = attachments;
        this.comment = comment;
    }

    public void setWriter(final User writer) {
        this.writer = writer;
    }

    public static class PostBuilder {
        private long postId;
        private String title;
        private int views;
        private int likes;
        private boolean isPrivate;
        private boolean isBlockComment;
        private Date postDate;
        private String content;
        private boolean isDelete;
        private boolean writer$set;
        private User writer$value;
        private boolean recommendation$set;
        private List<User> recommendation$value;
        private boolean tag$set;
        private List<Tag> tag$value;
        private boolean attachments$set;
        private List<Attachment> attachments$value;
        private boolean comment$set;
        private List<Comment> comment$value;

        PostBuilder() {
        }

        public PostBuilder postId(final long postId) {
            this.postId = postId;
            return this;
        }

        public PostBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public PostBuilder views(final int views) {
            this.views = views;
            return this;
        }

        public PostBuilder likes(final int likes) {
            this.likes = likes;
            return this;
        }

        public PostBuilder isPrivate(final boolean isPrivate) {
            this.isPrivate = isPrivate;
            return this;
        }

        public PostBuilder isBlockComment(final boolean isBlockComment) {
            this.isBlockComment = isBlockComment;
            return this;
        }

        @JsonFormat(
            shape = Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "Asia/Seoul"
        )
        public PostBuilder postDate(final Date postDate) {
            this.postDate = postDate;
            return this;
        }

        public PostBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public PostBuilder isDelete(final boolean isDelete) {
            this.isDelete = isDelete;
            return this;
        }

        public PostBuilder writer(final User writer) {
            this.writer$value = writer;
            this.writer$set = true;
            return this;
        }

        public PostBuilder recommendation(final List<User> recommendation) {
            this.recommendation$value = recommendation;
            this.recommendation$set = true;
            return this;
        }

        public PostBuilder tag(final List<Tag> tag) {
            this.tag$value = tag;
            this.tag$set = true;
            return this;
        }

        public PostBuilder attachments(final List<Attachment> attachments) {
            this.attachments$value = attachments;
            this.attachments$set = true;
            return this;
        }

        public PostBuilder comment(final List<Comment> comment) {
            this.comment$value = comment;
            this.comment$set = true;
            return this;
        }

        public Post build() {
            User writer$value = this.writer$value;
            if (!this.writer$set) {
                writer$value = Post.$default$writer();
            }

            List<User> recommendation$value = this.recommendation$value;
            if (!this.recommendation$set) {
                recommendation$value = Post.$default$recommendation();
            }

            List<Tag> tag$value = this.tag$value;
            if (!this.tag$set) {
                tag$value = Post.$default$tag();
            }

            List<Attachment> attachments$value = this.attachments$value;
            if (!this.attachments$set) {
                attachments$value = Post.$default$attachments();
            }

            List<Comment> comment$value = this.comment$value;
            if (!this.comment$set) {
                comment$value = Post.$default$comment();
            }

            return new Post(this.postId, this.title, this.views, this.likes, this.isPrivate, this.isBlockComment, this.postDate, this.content, this.isDelete, writer$value, recommendation$value, tag$value, attachments$value, comment$value);
        }

        public String toString() {
            return "Post.PostBuilder(postId=" + this.postId + ", title=" + this.title + ", views=" + this.views + ", likes=" + this.likes + ", isPrivate=" + this.isPrivate + ", isBlockComment=" + this.isBlockComment + ", postDate=" + this.postDate + ", content=" + this.content + ", isDelete=" + this.isDelete + ", writer$value=" + this.writer$value + ", recommendation$value=" + this.recommendation$value + ", tag$value=" + this.tag$value + ", attachments$value=" + this.attachments$value + ", comment$value=" + this.comment$value + ")";
        }
    }
}