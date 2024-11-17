package com.taehun.noticeboard.smtp.exception;

import com.taehun.noticeboard.common.response.message.MailMessage;
import com.taehun.noticeboard.common.response.message.AccountMessage;

public class MailException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MailException(String message) {
        super(message);
    }

    public MailException(AccountMessage message) {
        super(message.getMessage());
    }

    public MailException(MailMessage message) {
        super(message.getMessage());
    }
}