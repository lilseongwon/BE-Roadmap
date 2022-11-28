package com.example.chatexample.domain.user.service;

import com.example.chatexample.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.chatexample.domain.user.domain.User;
import com.example.chatexample.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LogoutService {

    private final UserFacade userFacade;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void execute() {

        User user = userFacade.getCurrentUser();

        refreshTokenRepository.findById(user.getEmail())
                .ifPresent(refreshTokenRepository::delete);
    }
}