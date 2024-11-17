package com.taehun.noticeboard.admin.report.content.repository;

import com.taehun.noticeboard.admin.report.content.dto.ContentReportResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CustomContentReportRepository {
    List<ContentReportResponse> findAllContentReport(Pageable pageable);
}
