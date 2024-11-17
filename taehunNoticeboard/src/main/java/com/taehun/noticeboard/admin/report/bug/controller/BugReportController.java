package com.taehun.noticeboard.admin.report.bug.controller;

import com.taehun.noticeboard.admin.report.bug.dto.BugReportRequest;
import com.taehun.noticeboard.admin.report.bug.service.BugReportService;
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
@RequestMapping({"/admin/report/bug"})
public class BugReportController {
    private final BugReportService bugReportService;

    @PostMapping
    public ResponseEntity createNewBugReport(@CookieValue String accessToken, @RequestBody BugReportRequest request) throws AccountException {
        this.bugReportService.createNewBugReport(accessToken, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity findAllBugReport(Pageable pageable) {
        return ResponseEntity.ok().body(this.bugReportService.findAllBugReport(pageable));
    }

    @GetMapping({"/count"})
    public ResponseEntity findAllBugReportCount() {
        return ResponseEntity.ok().body(this.bugReportService.findAllBugReportCount());
    }

    @PatchMapping({"/{reportId}"})
    public ResponseEntity modifySolvedInBugReport(@PathVariable long reportId) {
        this.bugReportService.modifySolvedInBugReport(reportId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping({"/{reportId}"})
    public ResponseEntity removeBugReport(@PathVariable long reportId) {
        this.bugReportService.removeBugReport(reportId);
        return ResponseEntity.noContent().build();
    }

    public BugReportController(final BugReportService bugReportService) {
        this.bugReportService = bugReportService;
    }
}
