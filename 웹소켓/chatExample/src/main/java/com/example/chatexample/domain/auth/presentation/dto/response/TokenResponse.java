package com.example.chatexample.domain.auth.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class TokenResponse {

    private final String accessToken;

    private final LocalDateTime expiredAt;

    private final String refreshToken;

}