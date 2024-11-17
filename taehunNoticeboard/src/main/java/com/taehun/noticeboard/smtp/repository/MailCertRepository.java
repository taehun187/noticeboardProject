package com.taehun.noticeboard.smtp.repository;

import com.taehun.noticeboard.smtp.entity.MailCert;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailCertRepository extends JpaRepository<MailCert, Long> {
    Optional<MailCert> findById(String id);

    long deleteById(String id);
}