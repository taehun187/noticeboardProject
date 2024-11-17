package com.taehun.noticeboard.admin.report.bug.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;

public class BugReportResponse {
    private long bugReportId;
    private String reporter;
    @JsonFormat(
        shape = Shape.STRING,
        pattern = "yyyy-MM-dd HH:MM:SS",
        timezone = "Asia/Seoul"
    )
    private LocalDateTime reportTime;
    private String content;
    private boolean isSolved;

    public BugReportResponse(final long bugReportId, final String reporter, final LocalDateTime reportTime, final String content, final boolean isSolved) {
        this.bugReportId = bugReportId;
        this.reporter = reporter;
        this.reportTime = reportTime;
        this.content = content;
        this.isSolved = isSolved;
    }

    public long getBugReportId() {
        return this.bugReportId;
    }

    public String getReporter() {
        return this.reporter;
    }

    public LocalDateTime getReportTime() {
        return this.reportTime;
    }

    public String getContent() {
        return this.content;
    }

    public boolean isSolved() {
        return this.isSolved;
    }
}
