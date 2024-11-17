package com.taehun.noticeboard.post.attachment.domain;

import com.taehun.noticeboard.post.post.domain.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Attachment {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long attachmentId;
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    @JoinColumn(
        nullable = false
    )
    private Post post;
    @Column(
        nullable = false,
        length = 50
    )
    private String realFileName;
    @Column(
        nullable = false,
        length = 50
    )
    private String uuidFileName;
    @Column(
        nullable = false,
        length = 100
    )
    private String s3Url;
    @Column(
        nullable = false
    )
    private long fileSize;

    public static Attachment createAttachment(String uuid, String s3Url, MultipartFile multipartFile) {
        return builder().realFileName(multipartFile.getOriginalFilename()).uuidFileName(uuid).s3Url(s3Url).fileSize(multipartFile.getSize()).build();
    }

    public static AttachmentBuilder builder() {
        return new AttachmentBuilder();
    }

    public long getAttachmentId() {
        return this.attachmentId;
    }

    public Post getPost() {
        return this.post;
    }

    public String getRealFileName() {
        return this.realFileName;
    }

    public String getUuidFileName() {
        return this.uuidFileName;
    }

    public String getS3Url() {
        return this.s3Url;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public Attachment() {
    }

    public Attachment(final long attachmentId, final Post post, final String realFileName, final String uuidFileName, final String s3Url, final long fileSize) {
        this.attachmentId = attachmentId;
        this.post = post;
        this.realFileName = realFileName;
        this.uuidFileName = uuidFileName;
        this.s3Url = s3Url;
        this.fileSize = fileSize;
    }

    public void setPost(final Post post) {
        this.post = post;
    }

    public static class AttachmentBuilder {
        private long attachmentId;
        private Post post;
        private String realFileName;
        private String uuidFileName;
        private String s3Url;
        private long fileSize;

        AttachmentBuilder() {
        }

        public AttachmentBuilder attachmentId(final long attachmentId) {
            this.attachmentId = attachmentId;
            return this;
        }

        public AttachmentBuilder post(final Post post) {
            this.post = post;
            return this;
        }

        public AttachmentBuilder realFileName(final String realFileName) {
            this.realFileName = realFileName;
            return this;
        }

        public AttachmentBuilder uuidFileName(final String uuidFileName) {
            this.uuidFileName = uuidFileName;
            return this;
        }

        public AttachmentBuilder s3Url(final String s3Url) {
            this.s3Url = s3Url;
            return this;
        }

        public AttachmentBuilder fileSize(final long fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        public Attachment build() {
            return new Attachment(this.attachmentId, this.post, this.realFileName, this.uuidFileName, this.s3Url, this.fileSize);
        }

        public String toString() {
            return "Attachment.AttachmentBuilder(attachmentId=" + this.attachmentId + ", post=" + this.post + ", realFileName=" + this.realFileName + ", uuidFileName=" + this.uuidFileName + ", s3Url=" + this.s3Url + ", fileSize=" + this.fileSize + ")";
        }
    }
}