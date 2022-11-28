package com.example.chatexample.domain.chat.service;

import com.example.chatexample.domain.chat.domain.RoomUser;
import com.example.chatexample.domain.chat.domain.repository.ChatRepository;
import com.example.chatexample.domain.chat.facade.RoomUserFacade;
import com.example.chatexample.domain.chat.presentation.dto.response.ChatResponse;
import com.example.chatexample.domain.chat.presentation.dto.response.QueryChatListResponse;
import com.example.chatexample.domain.user.domain.User;
import com.example.chatexample.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryChatListService {

    private final ChatRepository chatRepository;
    private final RoomUserFacade roomUserFacade;
    private final UserFacade userFacade;

    @Transactional
    public QueryChatListResponse execute(Long roomId, LocalDateTime localDateTime) {

        User user = userFacade.getCurrentUser();
        RoomUser roomUser = roomUserFacade.getById(roomId, user.getId());

        roomUser.updateLastReadTime();

        return new QueryChatListResponse(
                chatRepository.findTop100ByRoomAndCreatedAtBeforeOrderByIdAsc(roomUser.getRoom(), localDateTime)
                        .stream()
                        .map(c -> ChatResponse.of(c, c.getUser() == user))
                        .collect(Collectors.toList())
        );
    }
}