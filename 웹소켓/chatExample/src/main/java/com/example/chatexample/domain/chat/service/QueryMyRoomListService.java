package com.example.chatexample.domain.chat.service;

import com.example.chatexample.domain.chat.domain.repository.RoomUserRepository;
import com.example.chatexample.domain.chat.presentation.dto.response.QueryRoomListResponse;
import com.example.chatexample.domain.chat.presentation.dto.response.RoomResponse;
import com.example.chatexample.domain.user.domain.User;
import com.example.chatexample.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@RequiredArgsConstructor
@Service
public class QueryMyRoomListService {

    private final RoomUserRepository roomUserRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryRoomListResponse execute() {

        User user = userFacade.getCurrentUser();

        return new QueryRoomListResponse(
                roomUserRepository.findByUser(user)
                        .stream()
                        .map(RoomResponse::of)
                        .sorted(comparing(roomUser -> roomUser.getLastChat().getLastSentAt()))
                        .collect(Collectors.toList())
        );
    }

}