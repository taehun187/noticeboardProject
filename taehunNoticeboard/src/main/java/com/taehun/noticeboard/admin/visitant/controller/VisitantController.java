package com.taehun.noticeboard.admin.visitant.controller;

import com.taehun.noticeboard.admin.visitant.service.VisitantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/admin/visitant"})
public class VisitantController {
    private final VisitantService visitantService;

    @GetMapping({"/count"})
    public ResponseEntity findVisitantCountByVisitDate() {
        return ResponseEntity.ok(this.visitantService.findVisitantCountByVisitDate());
    }

    public VisitantController(final VisitantService visitantService) {
        this.visitantService = visitantService;
    }
}
