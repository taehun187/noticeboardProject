package com.taehun.noticeboard.admin.visitant.repository;

import com.taehun.noticeboard.admin.visitant.domain.Visitant;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitantRepository extends JpaRepository<Visitant, Long>, CustomVisitantRepository {
    boolean existsByUserIpAndVisitDate(String userIp, LocalDate visitDate);
}
