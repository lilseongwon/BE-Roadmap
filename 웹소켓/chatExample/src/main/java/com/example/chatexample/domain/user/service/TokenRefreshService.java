package com.example.chatexample.domain.user.service;

import com.example.chatexample.domain.auth.domain.RefreshToken;
import com.example.chatexample.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.chatexample.domain.auth.exception.RefreshTokenNotFoundException;
import com.example.chatexample.domain.auth.presentation.dto.response.TokenResponse;
import com.example.chatexample.global.security.jwt.JwtProperties;
import com.example.chatexample.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenRefreshService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional
    public TokenResponse execute(String refreshToken) {

        RefreshToken redisRefreshToken = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        String email = redisRefreshToken.getEmail();
        String newRefreshToken = jwtTokenProvider.createRefreshToken(email);

        redisRefreshToken.updateToken(newRefreshToken, jwtProperties.getRefreshExp());

        String newAccessToken = jwtTokenProvider.createAccessToken(email);

        return TokenResponse
                .builder()
                .accessToken(newAccessToken)
                .expiredAt(LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(newRefreshToken)
                .build();
    }
}