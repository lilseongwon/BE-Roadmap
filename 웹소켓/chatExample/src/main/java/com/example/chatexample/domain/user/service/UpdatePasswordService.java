package com.example.chatexample.domain.user.service;

import com.example.chatexample.domain.user.domain.User;
import com.example.chatexample.domain.user.facade.UserFacade;
import com.example.chatexample.domain.user.presentation.dto.request.UpdatePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdatePasswordService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(UpdatePasswordRequest request) {

        User user = userFacade.getCurrentUser();

        userFacade.checkPassword(user, request.getOldPassword());

        user.updatePassword(passwordEncoder.encode(request.getNewPassword()));
    }
}