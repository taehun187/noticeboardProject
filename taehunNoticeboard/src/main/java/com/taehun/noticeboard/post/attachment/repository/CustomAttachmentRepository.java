package com.taehun.noticeboard.post.attachment.repository;

import com.taehun.noticeboard.post.attachment.dto.AttachmentResponse;
import java.util.List;

public interface CustomAttachmentRepository {
    List<AttachmentResponse> findAttachmentsByPostId(long postId);
}