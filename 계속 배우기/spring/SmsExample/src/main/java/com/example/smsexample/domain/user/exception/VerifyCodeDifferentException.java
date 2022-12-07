package com.example.smsexample.domain.user.exception;


import com.example.smsexample.global.error.CustomException;
import com.example.smsexample.global.error.ErrorCode;

public class VerifyCodeDifferentException extends CustomException {

    public static final CustomException EXCEPTION =
            new VerifyCodeDifferentException();

    private VerifyCodeDifferentException() {
        super(ErrorCode.VERIFY_CODE_DIFFERENT);
    }
}
