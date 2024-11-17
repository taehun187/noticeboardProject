package com.taehun.noticeboard.common.response.message;

public enum S3Message {
    INVALID_FILE("올바르지 않은 파일입니다."),
    INVALID_IMAGE("올바르지 않은 이미지입니다.");

    private final String message;

    public String getMessage() {
        return this.message;
    }

    private S3Message(final String message) {
        this.message = message;
    }
}

