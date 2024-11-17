package com.taehun.noticeboard.post.attachment.repository;

import com.taehun.noticeboard.post.attachment.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long>, CustomAttachmentRepository {
}