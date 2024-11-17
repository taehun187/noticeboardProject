package com.taehun.noticeboard.admin.report.bug.repository;

import com.taehun.noticeboard.admin.report.bug.entity.BugReport;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugReportRepository extends JpaRepository<BugReport, Long>, CustomBugReportRepository {
    Optional<BugReport> findByBugReportId(long reportId);

    long countBy();
}
