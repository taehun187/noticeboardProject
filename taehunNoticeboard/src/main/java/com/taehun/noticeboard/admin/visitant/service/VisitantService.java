package com.taehun.noticeboard.admin.visitant.service;

import com.taehun.noticeboard.admin.visitant.dto.VisitantResponse;
import com.taehun.noticeboard.admin.visitant.repository.VisitantRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VisitantService {
    private final VisitantRepository visitantRepository;

    public List<VisitantResponse> findVisitantCountByVisitDate() {
        return this.visitantRepository.findVisitantCountByVisitDate();
    }

    public VisitantService(final VisitantRepository visitantRepository) {
        this.visitantRepository = visitantRepository;
    }
}