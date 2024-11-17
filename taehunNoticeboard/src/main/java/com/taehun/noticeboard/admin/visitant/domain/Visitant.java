package com.taehun.noticeboard.admin.visitant.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Visitant {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long visitantId;
    private String userIp;
    private String userAgent;
    private LocalDate visitDate;

    public static VisitantBuilder builder() {
        return new VisitantBuilder();
    }

    public long getVisitantId() {
        return this.visitantId;
    }

    public String getUserIp() {
        return this.userIp;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public LocalDate getVisitDate() {
        return this.visitDate;
    }

    public Visitant() {
    }

    public Visitant(final long visitantId, final String userIp, final String userAgent, final LocalDate visitDate) {
        this.visitantId = visitantId;
        this.userIp = userIp;
        this.userAgent = userAgent;
        this.visitDate = visitDate;
    }

    public static class VisitantBuilder {
        private long visitantId;
        private String userIp;
        private String userAgent;
        private LocalDate visitDate;

        VisitantBuilder() {
        }

        public VisitantBuilder visitantId(final long visitantId) {
            this.visitantId = visitantId;
            return this;
        }

        public VisitantBuilder userIp(final String userIp) {
            this.userIp = userIp;
            return this;
        }

        public VisitantBuilder userAgent(final String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public VisitantBuilder visitDate(final LocalDate visitDate) {
            this.visitDate = visitDate;
            return this;
        }

        public Visitant build() {
            return new Visitant(this.visitantId, this.userIp, this.userAgent, this.visitDate);
        }

        public String toString() {
            return "Visitant.VisitantBuilder(visitantId=" + this.visitantId + ", userIp=" + this.userIp + ", userAgent=" + this.userAgent + ", visitDate=" + this.visitDate + ")";
        }
    }
}
