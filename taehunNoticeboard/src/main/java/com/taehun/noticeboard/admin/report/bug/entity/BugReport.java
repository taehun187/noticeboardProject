package com.taehun.noticeboard.admin.report.bug.entity;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.common.config.BooleanConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class BugReport {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long bugReportId;
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    private User reporter;
    @CreationTimestamp
    private LocalDateTime reportTime;
    @Column(
        nullable = false
    )
    private String content;
    @Convert(
        converter = BooleanConverter.class
    )
    private boolean isSolved = false;

    public static BugReport createBugReport(User user, String content) {
        return builder().reporter(user).content(content).isSolved(false).build();
    }

    public void toggleSolved() {
        this.isSolved = !this.isSolved;
    }

    public static BugReportBuilder builder() {
        return new BugReportBuilder();
    }

    public long getBugReportId() {
        return this.bugReportId;
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

    public boolean isSolved() {
        return this.isSolved;
    }

    public BugReport(final long bugReportId, final User reporter, final LocalDateTime reportTime, final String content, final boolean isSolved) {
        this.bugReportId = bugReportId;
        this.reporter = reporter;
        this.reportTime = reportTime;
        this.content = content;
        this.isSolved = isSolved;
    }

    public BugReport() {
    }

    public static class BugReportBuilder {
        private long bugReportId;
        private User reporter;
        private LocalDateTime reportTime;
        private String content;
        private boolean isSolved;

        BugReportBuilder() {
        }

        public BugReportBuilder bugReportId(final long bugReportId) {
            this.bugReportId = bugReportId;
            return this;
        }

        public BugReportBuilder reporter(final User reporter) {
            this.reporter = reporter;
            return this;
        }

        public BugReportBuilder reportTime(final LocalDateTime reportTime) {
            this.reportTime = reportTime;
            return this;
        }

        public BugReportBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public BugReportBuilder isSolved(final boolean isSolved) {
            this.isSolved = isSolved;
            return this;
        }

        public BugReport build() {
            return new BugReport(this.bugReportId, this.reporter, this.reportTime, this.content, this.isSolved);
        }

        public String toString() {
            return "BugReport.BugReportBuilder(bugReportId=" + this.bugReportId + ", reporter=" + this.reporter + ", reportTime=" + this.reportTime + ", content=" + this.content + ", isSolved=" + this.isSolved + ")";
        }
    }
}
