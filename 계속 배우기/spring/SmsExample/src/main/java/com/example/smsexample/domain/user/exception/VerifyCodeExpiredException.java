package com.example.smsexample.domain.user.exception;


import com.example.smsexample.global.error.CustomException;
import com.example.smsexample.global.error.ErrorCode;

public class VerifyCodeExpiredException extends CustomException {

    public static final CustomException EXCEPTION =
            new VerifyCodeExpiredException();

    private VerifyCodeExpiredException() {
        super(ErrorCode.VERIFY_CODE_EXPIRED);
    }
}
