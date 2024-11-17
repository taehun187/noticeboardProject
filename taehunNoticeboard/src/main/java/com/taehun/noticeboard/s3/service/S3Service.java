package com.taehun.noticeboard.s3.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.taehun.noticeboard.common.response.message.S3Message;
import com.taehun.noticeboard.post.attachment.dto.AttachmentResponse;
import com.taehun.noticeboard.post.attachment.repository.AttachmentRepository;
import com.taehun.noticeboard.s3.exception.S3Exception;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3Service {
    private final AmazonS3Client amazonS3Client;
    private final AttachmentRepository attachmentRepository;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadFileToS3(MultipartFile file, String uuid) throws IOException {
        if (this.isValidFile(file)) {
            throw new S3Exception(S3Message.INVALID_FILE);
        } else {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength((long)file.getInputStream().available());
            this.amazonS3Client.putObject(this.bucket, uuid, file.getInputStream(), metadata);
            return this.amazonS3Client.getUrl(this.bucket, uuid).toString();
        }
    }

    public String uploadImageToS3(MultipartFile file) throws IOException {
        String uuid = UUID.randomUUID().toString();
        if (!this.isValidImage(file)) {
            throw new S3Exception(S3Message.INVALID_IMAGE);
        } else {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength((long)file.getInputStream().available());
            this.amazonS3Client.putObject(this.bucket, uuid, file.getInputStream(), metadata);
            return this.amazonS3Client.getUrl(this.bucket, uuid).toString();
        }
    }

    public boolean isValidFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return extension.equals("exe") && extension.equals("bat");
    }

    public boolean isValidImage(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return extension.equals("jpg") || extension.equals("png");
    }

    public void deleteFile(String fileName) {
        boolean isObjectExist = this.amazonS3Client.doesObjectExist(this.bucket, fileName);
        if (isObjectExist) {
            this.amazonS3Client.deleteObject(this.bucket, fileName);
        }

    }

    @Transactional
    public void deleteFileByPostId(long postId) {
        List<AttachmentResponse> attachmentResponses = this.attachmentRepository.findAttachmentsByPostId(postId);
        Iterator var4 = attachmentResponses.iterator();

        while(var4.hasNext()) {
            AttachmentResponse attachmentResponse = (AttachmentResponse)var4.next();
            this.deleteFile(attachmentResponse.getUuidFileName());
        }

    }

    public S3Service(final AmazonS3Client amazonS3Client, final AttachmentRepository attachmentRepository) {
        this.amazonS3Client = amazonS3Client;
        this.attachmentRepository = attachmentRepository;
    }
}