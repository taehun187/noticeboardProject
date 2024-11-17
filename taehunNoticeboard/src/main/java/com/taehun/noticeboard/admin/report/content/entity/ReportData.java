package com.taehun.noticeboard.admin.report.content.entity;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.admin.report.content.dto.ContentReportRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ReportData {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long reportDataId;
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    private User target;
    private String title;
    private String content;

    public static ReportData createReportData(User target, ContentReportRequest request) {
        return builder().target(target).title(request.getTarget().getTitle()).content(request.getTarget().getContent()).build();
    }

    public static ReportDataBuilder builder() {
        return new ReportDataBuilder();
    }

    public long getReportDataId() {
        return this.reportDataId;
    }

    public User getTarget() {
        return this.target;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public ReportData(final long reportDataId, final User target, final String title, final String content) {
        this.reportDataId = reportDataId;
        this.target = target;
        this.title = title;
        this.content = content;
    }

    public ReportData() {
    }

    public static class ReportDataBuilder {
        private long reportDataId;
        private User target;
        private String title;
        private String content;

        ReportDataBuilder() {
        }

        public ReportDataBuilder reportDataId(final long reportDataId) {
            this.reportDataId = reportDataId;
            return this;
        }

        public ReportDataBuilder target(final User target) {
            this.target = target;
            return this;
        }

        public ReportDataBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public ReportDataBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public ReportData build() {
            return new ReportData(this.reportDataId, this.target, this.title, this.content);
        }

        public String toString() {
            return "ReportData.ReportDataBuilder(reportDataId=" + this.reportDataId + ", target=" + this.target + ", title=" + this.title + ", content=" + this.content + ")";
        }
    }
}
