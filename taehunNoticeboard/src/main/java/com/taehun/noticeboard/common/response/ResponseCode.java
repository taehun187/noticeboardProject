package com.taehun.noticeboard.common.response;

public enum ResponseCode {
    REQUEST_FAIL("-1", "유효하지 않는 요청입니다."),
    RESPONSE_FAIL("-1", "요청에 응답하지 못했습니다."),
    INVALID_TOKEN("-2", "유효하지 않은 토큰입니다."),
    AUTHENTICATION_ERROR("-4", "인증되지 않은 사용자입니다."),
    AUTHORIZATION_ERROR("-4", "인가되지 않은 사용자입니다."),
    REQUEST_SUCCESS("1", "요청이 성공적으로 완료되었습니다."),
    LOGOUT_SUCCESS("1", "로그아웃에 성공하였습니다."),
    LOGIN_SUCCESS("1", "로그인에 성공하였습니다"),
    CREATE_ACCESS_TOKEN("4", "Access Token 이 재발급 되었습니다.");

    private final String code;
    private final String message;

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    private ResponseCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}