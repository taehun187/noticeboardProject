package com.taehun.noticeboard.admin.report.content.dto;

import com.taehun.noticeboard.admin.report.content.entity.ReportType;

public class ContentReportRequest {
    private String content;
    private ReportType reportType;
    private ReportTarget target;

    public ContentReportRequest() {
    }

    public String getContent() {
        return this.content;
    }

    public ReportType getReportType() {
        return this.reportType;
    }

    public ReportTarget getTarget() {
        return this.target;
    }

    public String toString() {
        String var10000 = this.getContent();
        return "ContentReportRequest(content=" + var10000 + ", reportType=" + this.getReportType() + ", target=" + this.getTarget() + ")";
    }

    public class ReportTarget {
        private String writer;
        private String title;
        private String content;

        public ReportTarget() {
        }

        public String getWriter() {
            return this.writer;
        }

        public String getTitle() {
            return this.title;
        }

        public String getContent() {
            return this.content;
        }

        public String toString() {
            String var10000 = this.getWriter();
            return "ContentReportRequest.ReportTarget(writer=" + var10000 + ", title=" + this.getTitle() + ", content=" + this.getContent() + ")";
        }
    }
}
