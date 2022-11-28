package com.example.chatexample.domain.chat.exception;


import com.example.chatexample.global.error.exception.BusinessException;
import com.example.chatexample.global.error.exception.ErrorCode;

public class RoomNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new RoomNotFoundException();
    private RoomNotFoundException(){
        super(ErrorCode.ROOM_NOT_FOUND);
    }
}