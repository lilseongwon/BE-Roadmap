package com.example.chatexample.global.socket.util;

import com.corundumstudio.socketio.SocketIOClient;
import com.example.chatexample.domain.chat.exception.InvalidUserException;
import com.example.chatexample.domain.chat.exception.RoomNotFoundException;
import com.example.chatexample.global.socket.security.ClientProperty;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SocketUtil {

    public static Long getUserId(SocketIOClient socketIOClient){

        if(!socketIOClient.has(ClientProperty.USER_KEY)) {
            throw InvalidUserException.EXCEPTION;
        }

        return socketIOClient.get(ClientProperty.USER_KEY);
    }

    public static Long getRoomId(SocketIOClient socketIOClient) {

        if(!socketIOClient.has(ClientProperty.ROOM_KEY)) {
            throw RoomNotFoundException.EXCEPTION;
        }

        return Long.valueOf(socketIOClient.get(ClientProperty.ROOM_KEY));
    }
}