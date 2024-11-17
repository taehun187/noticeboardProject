package com.taehun.noticeboard.account.user.exception;

import com.taehun.noticeboard.common.response.message.AccountMessage;

public class LoginException extends RuntimeException {
    public LoginException(AccountMessage message) {
        super(message.getMessage());
    }

    public LoginException(String message) {
        super(message);
    }
}
