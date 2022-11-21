package com.example.jwtexample.domain.auth.service;

import com.example.jwtexample.domain.auth.presentation.dto.response.TokenResponse;
import com.example.jwtexample.domain.user.domain.User;
import com.example.jwtexample.domain.user.exception.PasswordMismatchException;
import com.example.jwtexample.domain.user.facade.UserFacade;
import com.example.jwtexample.domain.user.presentation.dto.request.UserSigninRequest;
import com.example.jwtexample.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserSigninService {
    private final UserFacade userFacade;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenResponse execute(UserSigninRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        User user = userFacade.getUserByEmail(email);

        if(!passwordEncoder.matches(password, user.getPassword()))
            throw PasswordMismatchException.EXCEPTION;

        String accessToken = jwtTokenProvider.generateAccessToken(email);
        String refreshToken = jwtTokenProvider.generateRefreshToken(email);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}