package com.example.jwtexample.global.exception;


import com.example.jwtexample.global.error.CustomException;
import com.example.jwtexample.global.error.ErrorCode;

public class ExpiredJwtException extends CustomException {

    public static final CustomException EXCEPTION =
            new ExpiredJwtException();

    private ExpiredJwtException() {
        super(ErrorCode.EXPIRED_JWT);
    }
}