package com.example.jwtexample.domain.user.exception;

import com.example.jwtexample.global.error.CustomException;
import com.example.jwtexample.global.error.ErrorCode;

public class UserExistException extends CustomException {
    public static final CustomException EXCEPTION =
            new UserExistException();

    private UserExistException() {
        super(ErrorCode.USER_EXIST);
    }
}