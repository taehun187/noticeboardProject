package com.taehun.noticeboard.admin.report.content.dto;

import com.taehun.noticeboard.admin.report.content.entity.ReportType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;

public class ContentReportResponse {
    private long contentReportId;
    private String reporter;
    @JsonFormat(
        shape = Shape.STRING,
        pattern = "yyyy-MM-dd HH:MM:SS",
        timezone = "Asia/Seoul"
    )
    private LocalDateTime reportTime;
    private String content;
    private boolean isAction;
    private ReportType contentType;
    private ReportDataResponse reportData;

    public long getContentReportId() {
        return this.contentReportId;
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

    public boolean isAction() {
        return this.isAction;
    }

    public ReportType getContentType() {
        return this.contentType;
    }

    public ReportDataResponse getReportData() {
        return this.reportData;
    }

    public ContentReportResponse(final long contentReportId, final String reporter, final LocalDateTime reportTime, final String content, final boolean isAction, final ReportType contentType, final ReportDataResponse reportData) {
        this.contentReportId = contentReportId;
        this.reporter = reporter;
        this.reportTime = reportTime;
        this.content = content;
        this.isAction = isAction;
        this.contentType = contentType;
        this.reportData = reportData;
    }
}
