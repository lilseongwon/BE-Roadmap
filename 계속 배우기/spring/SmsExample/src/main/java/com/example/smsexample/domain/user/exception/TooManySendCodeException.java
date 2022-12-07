package com.example.smsexample.domain.user.exception;

import com.example.smsexample.global.error.CustomException;
import com.example.smsexample.global.error.ErrorCode;

public class TooManySendCodeException extends CustomException {

    public static final CustomException EXCEPTION =
            new TooManySendCodeException();

    private TooManySendCodeException() {
        super(ErrorCode.TOO_MANY_SEND_CODE);
    }
}
