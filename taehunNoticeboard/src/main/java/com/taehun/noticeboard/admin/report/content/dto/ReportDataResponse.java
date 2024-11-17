package com.taehun.noticeboard.admin.report.content.dto;

public class ReportDataResponse {
    private long reportDataId;
    private String targetId;
    private String title;
    private String content;

    public long getReportDataId() {
        return this.reportDataId;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public ReportDataResponse(final long reportDataId, final String targetId, final String title, final String content) {
        this.reportDataId = reportDataId;
        this.targetId = targetId;
        this.title = title;
        this.content = content;
    }
}
