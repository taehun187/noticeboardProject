package com.taehun.noticeboard.admin.visitant.dto;

import java.time.LocalDate;

public class VisitantResponse {
    private long visitorsCount;
    private LocalDate visitedDate;

    public long getVisitorsCount() {
        return this.visitorsCount;
    }

    public LocalDate getVisitedDate() {
        return this.visitedDate;
    }

    public VisitantResponse(final long visitorsCount, final LocalDate visitedDate) {
        this.visitorsCount = visitorsCount;
        this.visitedDate = visitedDate;
    }
}