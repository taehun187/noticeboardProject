package com.taehun.noticeboard.admin.report.bug.service;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.account.user.repository.LoginRepository;
import com.taehun.noticeboard.admin.report.bug.dto.BugReportRequest;
import com.taehun.noticeboard.admin.report.bug.dto.BugReportResponse;
import com.taehun.noticeboard.admin.report.bug.entity.BugReport;
import com.taehun.noticeboard.admin.report.bug.exception.BugReportException;
import com.taehun.noticeboard.admin.report.bug.repository.BugReportRepository;
import com.taehun.noticeboard.security.jwt.support.JwtTokenProvider;
import java.util.List;
import javax.security.auth.login.AccountException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BugReportService {
    private final JwtTokenProvider jwtTokenProvider;
    private final LoginRepository loginRepository;
    private final BugReportRepository bugReportRepository;

    public void createNewBugReport(String accessToken, BugReportRequest request) throws AccountException {
        String userId = this.jwtTokenProvider.getUserPk(accessToken);
        User user = (User)this.loginRepository.findById(userId).orElseThrow(() -> {
            return new AccountException("사용자를 찾을 수 없습니다.");
        });
        this.bugReportRepository.save(BugReport.createBugReport(user, request.getContent()));
    }

    public List<BugReportResponse> findAllBugReport(Pageable pageable) {
        return this.bugReportRepository.findAllBugReport(pageable);
    }

    public long findAllBugReportCount() {
        return this.bugReportRepository.countBy();
    }

    @Transactional
    public void modifySolvedInBugReport(long reportId) {
        BugReport bugReport = (BugReport)this.bugReportRepository.findByBugReportId(reportId).orElseThrow(() -> {
            return new BugReportException("찾을 수 없는 컨텐츠입니다.");
        });
        bugReport.toggleSolved();
    }

    @Transactional
    public void removeBugReport(long reportId) {
        this.bugReportRepository.deleteById(reportId);
    }

    public BugReportService(final JwtTokenProvider jwtTokenProvider, final LoginRepository loginRepository, final BugReportRepository bugReportRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.loginRepository = loginRepository;
        this.bugReportRepository = bugReportRepository;
    }
}
