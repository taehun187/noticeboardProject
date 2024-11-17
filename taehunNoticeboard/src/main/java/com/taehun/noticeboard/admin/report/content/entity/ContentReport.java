package com.taehun.noticeboard.admin.report.content.entity;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.admin.report.content.dto.ContentReportRequest;
import com.taehun.noticeboard.common.config.BooleanConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class ContentReport {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long contentReportId;
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    private User reporter;
    @CreationTimestamp
    @Column(
        nullable = false
    )
    private LocalDateTime reportTime;
    @Column(
        nullable = false
    )
    private String content;
    @Convert(
        converter = BooleanConverter.class
    )
    @Column(
        nullable = false
    )
    private boolean isAction;
    @Enumerated(EnumType.STRING)
    @Column(
        nullable = false
    )
    private ReportType reportType;
    @OneToOne(
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    private ReportData reportData;

    public static ContentReport createContentReport(User user, ContentReportRequest request, ReportData data) {
        return builder().reporter(user).content(request.getContent()).isAction(false).reportType(request.getReportType()).reportData(data).build();
    }

    public void toggleIsAction() {
        this.isAction = !this.isAction;
    }

    public static ContentReportBuilder builder() {
        return new ContentReportBuilder();
    }

    public long getContentReportId() {
        return this.contentReportId;
    }

    public User getReporter() {
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

    public ReportType getReportType() {
        return this.reportType;
    }

    public ReportData getReportData() {
        return this.reportData;
    }

    public ContentReport(final long contentReportId, final User reporter, final LocalDateTime reportTime, final String content, final boolean isAction, final ReportType reportType, final ReportData reportData) {
        this.contentReportId = contentReportId;
        this.reporter = reporter;
        this.reportTime = reportTime;
        this.content = content;
        this.isAction = isAction;
        this.reportType = reportType;
        this.reportData = reportData;
    }

    public ContentReport() {
    }

    public static class ContentReportBuilder {
        private long contentReportId;
        private User reporter;
        private LocalDateTime reportTime;
        private String content;
        private boolean isAction;
        private ReportType reportType;
        private ReportData reportData;

        ContentReportBuilder() {
        }

        public ContentReportBuilder contentReportId(final long contentReportId) {
            this.contentReportId = contentReportId;
            return this;
        }

        public ContentReportBuilder reporter(final User reporter) {
            this.reporter = reporter;
            return this;
        }

        public ContentReportBuilder reportTime(final LocalDateTime reportTime) {
            this.reportTime = reportTime;
            return this;
        }

        public ContentReportBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public ContentReportBuilder isAction(final boolean isAction) {
            this.isAction = isAction;
            return this;
        }

        public ContentReportBuilder reportType(final ReportType reportType) {
            this.reportType = reportType;
            return this;
        }

        public ContentReportBuilder reportData(final ReportData reportData) {
            this.reportData = reportData;
            return this;
        }

        public ContentReport build() {
            return new ContentReport(this.contentReportId, this.reporter, this.reportTime, this.content, this.isAction, this.reportType, this.reportData);
        }

        public String toString() {
            return "ContentReport.ContentReportBuilder(contentReportId=" + this.contentReportId + ", reporter=" + this.reporter + ", reportTime=" + this.reportTime + ", content=" + this.content + ", isAction=" + this.isAction + ", reportType=" + this.reportType + ", reportData=" + this.reportData + ")";
        }
    }
}
