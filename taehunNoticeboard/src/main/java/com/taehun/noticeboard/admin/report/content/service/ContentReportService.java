package com.taehun.noticeboard.admin.report.content.service;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.account.user.repository.LoginRepository;
import com.taehun.noticeboard.admin.report.content.dto.ContentReportRequest;
import com.taehun.noticeboard.admin.report.content.dto.ContentReportResponse;
import com.taehun.noticeboard.admin.report.content.entity.ContentReport;
import com.taehun.noticeboard.admin.report.content.entity.ReportData;
import com.taehun.noticeboard.admin.report.content.exception.ContentReportException;
import com.taehun.noticeboard.admin.report.content.repository.ContentReportRepository;
import com.taehun.noticeboard.security.jwt.support.JwtTokenProvider;
import java.util.List;
import javax.security.auth.login.AccountException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContentReportService {
    private final ContentReportRepository contentReportRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final LoginRepository loginRepository;

    @Transactional
    public void createNewContentReport(String accessToken, ContentReportRequest request) throws AccountException {
        String userId = this.jwtTokenProvider.getUserPk(accessToken);
        User user = (User)this.loginRepository.findById(userId).orElseThrow(() -> {
            return new AccountException("사용자를 찾을 수 없습니다.");
        });
        ReportData reportData = this.createReportData(request);
        this.contentReportRepository.save(ContentReport.createContentReport(user, request, reportData));
    }

    public ReportData createReportData(ContentReportRequest request) throws AccountException {
        User targetUser = (User)this.loginRepository.findById(request.getTarget().getWriter()).orElseThrow(() -> {
            return new AccountException("사용자를 찾을 수 없습니다.");
        });
        return ReportData.createReportData(targetUser, request);
    }

    public List<ContentReportResponse> findAllContentReport(Pageable pageable) {
        return this.contentReportRepository.findAllContentReport(pageable);
    }

    public long findAllContentReportCount() {
        return this.contentReportRepository.countBy();
    }

    @Transactional
    public void toggleIsSolved(long reportId) {
        ContentReport report = (ContentReport)this.contentReportRepository.findById(reportId).orElseThrow(() -> {
            return new ContentReportException("해당 컨텐츠를 찾을 수 없습니다.");
        });
        report.toggleIsAction();
    }

    @Transactional
    public void removeContentReport(long reportId) {
        this.contentReportRepository.deleteById(reportId);
    }

    public ContentReportService(final ContentReportRepository contentReportRepository, final JwtTokenProvider jwtTokenProvider, final LoginRepository loginRepository) {
        this.contentReportRepository = contentReportRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.loginRepository = loginRepository;
    }
}
