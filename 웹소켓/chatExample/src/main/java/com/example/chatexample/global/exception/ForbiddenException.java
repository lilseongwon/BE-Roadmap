package com.example.chatexample.global.exception;

import com.example.chatexample.global.error.exception.BusinessException;
import com.example.chatexample.global.error.exception.ErrorCode;

public class ForbiddenException extends BusinessException {
    public static final BusinessException EXCEPTION = new ForbiddenException();
    private ForbiddenException(){
        super(ErrorCode.FORBIDDEN);
    }
}