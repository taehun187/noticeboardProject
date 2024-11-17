package com.taehun.noticeboard.common.response.message;

public enum PostMessage {
    NOT_FOUNT_POST("게시글을 찾을 수 없습니다."),
    ALREADY_RECOMMENDED("이미 추천된 게시글입니다."),
    ONLY_OWNER_CAN_DELETE("본인이 작성한 포스트만 삭제할 수 있습니다."),
    ONLY_OWNER_CAN_MODIFY("본인이 작성한 포스트만 수정할 수 있습니다."),
    IS_DELETE_POST("삭제된 게시글입니다.");

    private final String message;

    public String getMessage() {
        return this.message;
    }

    private PostMessage(final String message) {
        this.message = message;
    }
}
