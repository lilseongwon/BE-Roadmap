package com.example.chatexample.domain.user.exception;


import com.example.chatexample.global.error.exception.BusinessException;
import com.example.chatexample.global.error.exception.ErrorCode;

public class UnverifiedEmailException extends BusinessException {
    public static final BusinessException EXCEPTION = new UnverifiedEmailException();
    private UnverifiedEmailException() {
        super(ErrorCode.UNVERIFIED_EMAIL);
    }
}