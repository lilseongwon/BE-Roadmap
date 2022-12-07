package com.example.smsexample.domain.user.exception;

import com.example.smsexample.global.error.CustomException;
import com.example.smsexample.global.error.ErrorCode;

public class UserExistsException extends CustomException {

    public static final CustomException EXCEPTION =
            new UserExistsException();

    private UserExistsException() {
        super(ErrorCode.USER_EXISTS);
    }
}