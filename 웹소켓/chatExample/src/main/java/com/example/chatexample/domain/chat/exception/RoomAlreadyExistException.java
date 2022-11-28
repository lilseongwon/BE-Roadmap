package com.example.chatexample.domain.chat.exception;

import com.example.chatexample.global.error.exception.BusinessException;
import com.example.chatexample.global.error.exception.ErrorCode;

public class RoomAlreadyExistException extends BusinessException {
    public static final BusinessException EXCEPTION = new RoomAlreadyExistException();
    private RoomAlreadyExistException(){
        super(ErrorCode.ROOM_ALREADY_EXIST);
    }
}