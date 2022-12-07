package com.example.smsexample.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    VERIFY_CODE_DIFFERENT(401, "인증코드가 일치하지 않음"),

    VERIFY_CODE_EXPIRED(403, "인증 코드가 만료됨"),
    INVALID_CODE_TYPE(403, "인증 코드 타입이 다름"),

    USER_EXISTS(409, "유저 아이디 중복"),
    ALREADY_CHECK(409, "이미 체크함"),

    TOO_MANY_SEND_CODE(429, "너무 많은 인증코드 요청"),

    INTERNAL_SERVER_ERROR(500, "서버 에러"),
    SEND_CODE_ERROR(500, "Coolsms 에러");

    private final int status;
    private final String message;
}