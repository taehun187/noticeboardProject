package com.taehun.noticeboard.smtp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MailCert {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long mailCertId;
    @Column(
        nullable = false
    )
    private String id;
    @Column(
        nullable = false
    )
    private String verificationCode;

    public boolean isCorrectVerificationCode(String code) {
        return this.verificationCode.equals(code);
    }

    public static MailCert createMailCert(String id, String code) {
        return builder().id(id).verificationCode(code).build();
    }

    public static MailCertBuilder builder() {
        return new MailCertBuilder();
    }

    public MailCert(final long mailCertId, final String id, final String verificationCode) {
        this.mailCertId = mailCertId;
        this.id = id;
        this.verificationCode = verificationCode;
    }

    public MailCert() {
    }

    public long getMailCertId() {
        return this.mailCertId;
    }

    public String getId() {
        return this.id;
    }

    public String getVerificationCode() {
        return this.verificationCode;
    }

    public static class MailCertBuilder {
        private long mailCertId;
        private String id;
        private String verificationCode;

        MailCertBuilder() {
        }

        public MailCertBuilder mailCertId(final long mailCertId) {
            this.mailCertId = mailCertId;
            return this;
        }

        public MailCertBuilder id(final String id) {
            this.id = id;
            return this;
        }

        public MailCertBuilder verificationCode(final String verificationCode) {
            this.verificationCode = verificationCode;
            return this;
        }

        public MailCert build() {
            return new MailCert(this.mailCertId, this.id, this.verificationCode);
        }

        public String toString() {
            return "MailCert.MailCertBuilder(mailCertId=" + this.mailCertId + ", id=" + this.id + ", verificationCode=" + this.verificationCode + ")";
        }
    }
}