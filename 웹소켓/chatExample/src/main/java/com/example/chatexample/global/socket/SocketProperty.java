package com.example.chatexample.global.socket;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SocketProperty {

    //request
    public static final String CHAT = "chat";

    public static final String JOIN = "join";

    //response
    public static final String ERROR = "error";
}