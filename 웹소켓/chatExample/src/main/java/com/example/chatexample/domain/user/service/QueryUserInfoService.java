package com.example.chatexample.domain.user.service;

import com.example.chatexample.domain.user.domain.User;
import com.example.chatexample.domain.user.facade.UserFacade;
import com.example.chatexample.domain.user.presentation.dto.response.QueryUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryUserInfoService {

    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryUserInfoResponse execute(Long userId) {

        User user = userFacade.getUserById(userId);

        return QueryUserInfoResponse
                .builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}