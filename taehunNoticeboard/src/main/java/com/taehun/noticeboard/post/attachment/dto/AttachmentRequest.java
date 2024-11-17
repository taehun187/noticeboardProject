package com.taehun.noticeboard.post.attachment.dto;

import java.util.Arrays;

public class AttachmentRequest {
    private long[] attachmentId;

    public static AttachmentRequestBuilder builder() {
        return new AttachmentRequestBuilder();
    }

    public long[] getAttachmentId() {
        return this.attachmentId;
    }

    public AttachmentRequest() {
    }

    public AttachmentRequest(final long[] attachmentId) {
        this.attachmentId = attachmentId;
    }

    public static class AttachmentRequestBuilder {
        private long[] attachmentId;

        AttachmentRequestBuilder() {
        }

        public AttachmentRequestBuilder attachmentId(final long[] attachmentId) {
            this.attachmentId = attachmentId;
            return this;
        }

        public AttachmentRequest build() {
            return new AttachmentRequest(this.attachmentId);
        }

        public String toString() {
            return "AttachmentRequest.AttachmentRequestBuilder(attachmentId=" + Arrays.toString(this.attachmentId) + ")";
        }
    }
}