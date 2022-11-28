package com.example.chatexample.global.socket.exception;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ExceptionListener;
import com.example.chatexample.global.error.ErrorResponse;
import com.example.chatexample.global.error.exception.BusinessException;
import com.example.chatexample.global.error.exception.ErrorCode;
import com.example.chatexample.global.socket.SocketProperty;
import io.netty.channel.ChannelHandlerContext;

import java.util.Arrays;
import java.util.List;

public class SocketExceptionListener implements ExceptionListener {
    @Override
    public void onEventException(Exception e, List<Object> args, SocketIOClient client) {
        runExceptionHandling(e, client);
    }

    @Override
    public void onDisconnectException(Exception e, SocketIOClient client) {
        runExceptionHandling(e, client);
    }

    @Override
    public void onConnectException(Exception e, SocketIOClient client) {
        runExceptionHandling(e, client);
        client.disconnect();
    }

    @Override
    public void onPingException(Exception e, SocketIOClient client) {
        runExceptionHandling(e, client);
    }

    @Override
    public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        return false;
    }

    private void runExceptionHandling(Exception e, SocketIOClient client) {

        ErrorCode errorCode;

        System.out.println(e.getCause());
        System.out.println(e.getMessage());
        System.out.println(e.getClass());

        System.out.println(e.getCause().getMessage());
        Arrays.stream(e.getCause().getStackTrace()).forEach(System.out::println);

        if (e.getCause() instanceof BusinessException) {
            errorCode = ((BusinessException) e.getCause()).getErrorCode();
        } else {
            errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        }

        ErrorResponse message = ErrorResponse.builder()
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        client.sendEvent(SocketProperty.ERROR, message);
    }
}