package com.example.smsexample.domain.user.exception;

import com.example.smsexample.global.error.CustomException;
import com.example.smsexample.global.error.ErrorCode;

public class SendCodeErrorException extends CustomException {

    public static final CustomException EXCEPTION =
            new SendCodeErrorException();

    private SendCodeErrorException() {
        super(ErrorCode.SEND_CODE_ERROR);
    }
}
