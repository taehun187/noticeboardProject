package com.taehun.noticeboard.common.response.message;

public enum CommentMessage {
    ONLY_OWNER_CAN_DELETE("댓글 작성자만 제거할 수 있습니다.");

    private final String message;

    public String getMessage() {
        return this.message;
    }

    private CommentMessage(final String message) {
        this.message = message;
    }
}
