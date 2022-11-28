package com.example.chatexample.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class QueryUserInfoResponse {

    private final Long userId;
    private final String name;
    private final String email;
}