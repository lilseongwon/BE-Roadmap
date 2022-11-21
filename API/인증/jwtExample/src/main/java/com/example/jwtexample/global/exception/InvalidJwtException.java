package com.example.jwtexample.global.exception;


import com.example.jwtexample.global.error.CustomException;
import com.example.jwtexample.global.error.ErrorCode;

public class InvalidJwtException extends CustomException {

    public static final CustomException EXCEPTION =
            new InvalidJwtException();

    private InvalidJwtException() {
        super(ErrorCode.INVALID_JWT);
    }
}