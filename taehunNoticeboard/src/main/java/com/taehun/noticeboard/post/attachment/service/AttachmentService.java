package com.taehun.noticeboard.post.attachment.service;

import com.taehun.noticeboard.post.attachment.domain.Attachment;
import com.taehun.noticeboard.post.attachment.dto.AttachmentResponse;
import com.taehun.noticeboard.post.attachment.repository.AttachmentRepository;
import com.taehun.noticeboard.post.post.domain.Post;
import com.taehun.noticeboard.s3.service.S3Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final S3Service s3Service;

    @Transactional
    public void fileUpload(List<MultipartFile> multipartFiles, Post post) throws IOException {
        if (multipartFiles != null) {
            Iterator var3 = multipartFiles.iterator();

            while(var3.hasNext()) {
                MultipartFile multipartFile = (MultipartFile)var3.next();
                String uuid = this.createUUIDString();
                String s3Url = this.s3Service.uploadFileToS3(multipartFile, uuid);
                Attachment attachment = Attachment.createAttachment(uuid, s3Url, multipartFile);
                post.addFreeAttach(attachment);
            }
        }

    }

    public String createUUIDString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Transactional
    public void deletedAttachment(long[] deletedFileIds, long postId) {
        List<AttachmentResponse> attachments = this.attachmentRepository.findAttachmentsByPostId(postId);
        List<Long> deletedAttachmentId = (List)Arrays.stream(deletedFileIds).boxed().collect(Collectors.toCollection(ArrayList::new));
        Iterator var6 = attachments.iterator();

        while(var6.hasNext()) {
            AttachmentResponse attachmentResponse = (AttachmentResponse)var6.next();
            if (deletedAttachmentId.contains(attachmentResponse.getAttachmentId())) {
                this.attachmentRepository.deleteById(attachmentResponse.getAttachmentId());
                this.s3Service.deleteFile(attachmentResponse.getUuidFileName());
            }
        }

    }

    public AttachmentService(final AttachmentRepository attachmentRepository, final S3Service s3Service) {
        this.attachmentRepository = attachmentRepository;
        this.s3Service = s3Service;
    }
}