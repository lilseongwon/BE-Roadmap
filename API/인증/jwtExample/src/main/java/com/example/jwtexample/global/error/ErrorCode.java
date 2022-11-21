package com.example.jwtexample.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    PASSWORD_MISMATCH(401-1, "Password Mismatch"),

    EXPIRED_JWT(401-2, "Expired Jwt"),
    SIGNATURE_JWT(401-3, "Signature Jwt"),
    INVALID_JWT(401-4, "Invalid Jwt"),

    INVALID_REFRESH_TOKEN(401-5, "Invalid Refresh Token"),

    USER_NOT_FOUND(404-1, "User Not Found"),

    REFRESH_TOKEN_NOT_FOUND(404-3, "RefreshToken Not Found"),

    USER_EXIST(409, "User Exist"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int status;
    private final String message;
}