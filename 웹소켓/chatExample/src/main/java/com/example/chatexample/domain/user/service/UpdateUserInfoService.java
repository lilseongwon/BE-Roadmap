package com.example.chatexample.domain.user.service;

import com.example.chatexample.domain.user.domain.User;
import com.example.chatexample.domain.user.facade.UserFacade;
import com.example.chatexample.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateUserInfoService {

    private final UserFacade userFacade;

    @Transactional
    public void execute(UpdateUserInfoRequest request) {

        User user = userFacade.getCurrentUser();

        user.updateInfo(request);
    }
}