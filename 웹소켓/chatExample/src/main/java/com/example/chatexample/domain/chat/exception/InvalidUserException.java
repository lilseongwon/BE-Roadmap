package com.example.chatexample.domain.chat.exception;

import com.example.chatexample.global.error.exception.BusinessException;
import com.example.chatexample.global.error.exception.ErrorCode;

public class InvalidUserException extends BusinessException {
    public static final BusinessException EXCEPTION = new InvalidUserException();
    private InvalidUserException() {
        super(ErrorCode.INVALID_USER);
    }
}