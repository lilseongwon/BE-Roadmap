package com.example.chatexample.global.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private final int status;
    private final String code;
    private final String message;
}