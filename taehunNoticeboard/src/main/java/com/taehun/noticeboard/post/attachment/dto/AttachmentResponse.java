package com.taehun.noticeboard.post.attachment.dto;

public class AttachmentResponse {
    private long attachmentId;
    private String realFileName;
    private String s3Url;
    private long fileSize;
    private String uuidFileName;

    public static AttachmentResponseBuilder builder() {
        return new AttachmentResponseBuilder();
    }

    public long getAttachmentId() {
        return this.attachmentId;
    }

    public String getRealFileName() {
        return this.realFileName;
    }

    public String getS3Url() {
        return this.s3Url;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public String getUuidFileName() {
        return this.uuidFileName;
    }

    public AttachmentResponse() {
    }

    public AttachmentResponse(final long attachmentId, final String realFileName, final String s3Url, final long fileSize, final String uuidFileName) {
        this.attachmentId = attachmentId;
        this.realFileName = realFileName;
        this.s3Url = s3Url;
        this.fileSize = fileSize;
        this.uuidFileName = uuidFileName;
    }

    public static class AttachmentResponseBuilder {
        private long attachmentId;
        private String realFileName;
        private String s3Url;
        private long fileSize;
        private String uuidFileName;

        AttachmentResponseBuilder() {
        }

        public AttachmentResponseBuilder attachmentId(final long attachmentId) {
            this.attachmentId = attachmentId;
            return this;
        }

        public AttachmentResponseBuilder realFileName(final String realFileName) {
            this.realFileName = realFileName;
            return this;
        }

        public AttachmentResponseBuilder s3Url(final String s3Url) {
            this.s3Url = s3Url;
            return this;
        }

        public AttachmentResponseBuilder fileSize(final long fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        public AttachmentResponseBuilder uuidFileName(final String uuidFileName) {
            this.uuidFileName = uuidFileName;
            return this;
        }

        public AttachmentResponse build() {
            return new AttachmentResponse(this.attachmentId, this.realFileName, this.s3Url, this.fileSize, this.uuidFileName);
        }

        public String toString() {
            return "AttachmentResponse.AttachmentResponseBuilder(attachmentId=" + this.attachmentId + ", realFileName=" + this.realFileName + ", s3Url=" + this.s3Url + ", fileSize=" + this.fileSize + ", uuidFileName=" + this.uuidFileName + ")";
        }
    }
}