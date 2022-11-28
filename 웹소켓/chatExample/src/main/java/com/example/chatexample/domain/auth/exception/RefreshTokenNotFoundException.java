package com.example.chatexample.domain.auth.exception;

import com.example.chatexample.global.error.exception.BusinessException;
import com.example.chatexample.global.error.exception.ErrorCode;

public class RefreshTokenNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new RefreshTokenNotFoundException();
    private RefreshTokenNotFoundException(){
        super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}