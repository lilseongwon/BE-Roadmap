package com.example.chatexample.domain.user.exception;


import com.example.chatexample.global.error.exception.BusinessException;
import com.example.chatexample.global.error.exception.ErrorCode;

public class PasswordMismatchException extends BusinessException {
    public static final BusinessException EXCEPTION = new PasswordMismatchException();
    private PasswordMismatchException(){
        super(ErrorCode.PASSWORD_NOT_VALID);
    }
}