package com.taehun.noticeboard.admin.report.content.controller;

import com.taehun.noticeboard.admin.report.content.dto.ContentReportRequest;
import com.taehun.noticeboard.admin.report.content.service.ContentReportService;
import javax.security.auth.login.AccountException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/admin/report/content"})
public class ContentReportController {
    private final ContentReportService contentReportService;

    @PostMapping
    public ResponseEntity createNewContentReport(@CookieValue String accessToken, @RequestBody ContentReportRequest request) throws AccountException {
        this.contentReportService.createNewContentReport(accessToken, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity findAllContentReport(Pageable pageable) {
        return ResponseEntity.ok(this.contentReportService.findAllContentReport(pageable));
    }

    @GetMapping({"/count"})
    public ResponseEntity findAllContentReportCount() {
        return ResponseEntity.ok(this.contentReportService.findAllContentReportCount());
    }

    @PatchMapping({"/{reportId}"})
    public ResponseEntity toggleIsSolved(@PathVariable long reportId) {
        this.contentReportService.toggleIsSolved(reportId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping({"/{reportId}"})
    public ResponseEntity removeContentReport(@PathVariable long reportId) {
        this.contentReportService.removeContentReport(reportId);
        return ResponseEntity.noContent().build();
    }

    public ContentReportController(final ContentReportService contentReportService) {
        this.contentReportService = contentReportService;
    }
}
