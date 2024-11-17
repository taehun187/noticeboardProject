package com.taehun.noticeboard.admin.visitant.repository;

import com.taehun.noticeboard.admin.visitant.dto.VisitantResponse;
import java.util.List;

public interface CustomVisitantRepository {
    List<VisitantResponse> findVisitantCountByVisitDate();
}