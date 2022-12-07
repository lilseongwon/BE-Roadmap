package com.example.smsexample.domain.user.exception;

import com.example.smsexample.global.error.CustomException;
import com.example.smsexample.global.error.ErrorCode;

public class InvalidCodeTypeException extends CustomException {

    public static final CustomException EXCEPTION =
            new InvalidCodeTypeException();

    private InvalidCodeTypeException() {
        super(ErrorCode.INVALID_CODE_TYPE);
    }
}
