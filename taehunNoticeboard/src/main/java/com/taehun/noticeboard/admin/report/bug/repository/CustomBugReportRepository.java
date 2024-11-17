package com.taehun.noticeboard.admin.report.bug.repository;

import com.taehun.noticeboard.admin.report.bug.dto.BugReportResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CustomBugReportRepository {
    List<BugReportResponse> findAllBugReport(Pageable pageable);
}
