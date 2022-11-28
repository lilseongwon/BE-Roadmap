package com.example.chatexample.domain.chat.exception;


import com.example.chatexample.global.error.exception.BusinessException;
import com.example.chatexample.global.error.exception.ErrorCode;

public class RoomUserNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new RoomUserNotFoundException();
    private RoomUserNotFoundException(){
        super(ErrorCode.ROOM_USER_NOT_FOUND);
    }
}