package com.taehun.noticeboard.smtp.controller;

import com.taehun.noticeboard.smtp.dto.CertRequest;
import com.taehun.noticeboard.smtp.service.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MailController {
    private final MailService mailService;

    @PostMapping({"/mail/send"})
    public ResponseEntity sendMail(@RequestBody CertRequest request) {
        this.mailService.sendMail(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping({"/mail/check"})
    public ResponseEntity checkMail(@RequestBody CertRequest request) {
        this.mailService.checkVerificationCode(request);
        return ResponseEntity.ok().build();
    }

    public MailController(final MailService mailService) {
        this.mailService = mailService;
    }
}