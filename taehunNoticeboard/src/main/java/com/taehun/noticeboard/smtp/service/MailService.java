package com.taehun.noticeboard.smtp.service;

import com.taehun.noticeboard.common.response.message.MailMessage;
import com.taehun.noticeboard.smtp.dto.CertRequest;
import com.taehun.noticeboard.smtp.entity.MailCert;
import com.taehun.noticeboard.smtp.exception.MailException;
import com.taehun.noticeboard.smtp.repository.MailCertRepository;
import com.taehun.noticeboard.smtp.util.MailUtil;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MailService {
    private final MailUtil mailUtil;
    private final MailCertRepository mailCertRepository;

    @Transactional
    public void sendMail(CertRequest request) {
        MailCert mailCert = this.createVerification(request.getEmail());
        if (!this.mailUtil.sendMail(request.getEmail(), mailCert.getVerificationCode())) {
            throw new MailException(MailMessage.SMTP_SERVER_ERROR);
        }
    }

    private MailCert createVerification(String id) {
        String code = this.createVerificationCode();
        MailCert mailCert = this.createVerificationCode(id, code);
        return (MailCert)this.mailCertRepository.save(mailCert);
    }

    private MailCert createVerificationCode(String id, String code) {
        MailCert mailCert = (MailCert)this.mailCertRepository.findById(id).orElseGet(() -> {
            return MailCert.createMailCert(id, code);
        });
        return mailCert;
    }

    @Transactional
    public void checkVerificationCode(CertRequest request) {
        if (this.isCorrectVerificationCode(request)) {
            this.mailCertRepository.deleteById(request.getEmail());
        }

    }

    private boolean isCorrectVerificationCode(CertRequest request) {
        MailCert mailCert = (MailCert)this.mailCertRepository.findById(request.getEmail()).orElseThrow(() -> {
            return new MailException(MailMessage.UNUSUAL_APPROACH);
        });
        if (!mailCert.isCorrectVerificationCode(request.getCode())) {
            throw new MailException(MailMessage.NOT_MATCHED_CODE);
        } else {
            return true;
        }
    }

    private String createVerificationCode() {
        String code = UUID.randomUUID().toString();
        return code;
    }

    public MailService(final MailUtil mailUtil, final MailCertRepository mailCertRepository) {
        this.mailUtil = mailUtil;
        this.mailCertRepository = mailCertRepository;
    }
}