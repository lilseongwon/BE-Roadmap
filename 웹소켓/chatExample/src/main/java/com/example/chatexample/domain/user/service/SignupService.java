package com.example.chatexample.domain.user.service;

import com.example.chatexample.domain.auth.presentation.dto.response.TokenResponse;
import com.example.chatexample.domain.user.domain.User;
import com.example.chatexample.domain.user.domain.repository.UserRepository;
import com.example.chatexample.domain.user.exception.UserAlreadyExistException;
import com.example.chatexample.domain.user.presentation.dto.request.SignupRequest;
import com.example.chatexample.global.security.jwt.JwtProperties;
import com.example.chatexample.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenResponse execute(SignupRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw UserAlreadyExistException.EXCEPTION;
        }

        userRepository.save(User
                .builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build()
        );

        String accessToken = jwtTokenProvider.createAccessToken(request.getEmail());
        String refreshToken = jwtTokenProvider.createRefreshToken(request.getEmail());

        return TokenResponse
                .builder()
                .accessToken(accessToken)
                .expiredAt(LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(refreshToken)
                .build();
    }
}