package com.example.chatexample.domain.chat.presentation.dto.response;

import com.example.chatexample.domain.chat.domain.Chat;
import com.example.chatexample.domain.user.domain.User;
import com.example.chatexample.domain.user.presentation.dto.response.UserResponse;
import com.example.chatexample.global.util.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ChatResponse {

    @JsonProperty("room_id")
    private Long roomId;

    @JsonProperty("is_mine")
    private Boolean isMine;

    @JsonProperty("sent_at")
    private String sentAt;

    @JsonProperty("user")
    private UserResponse user;

    @JsonProperty("message")
    private String message;

    public static ChatResponse of(Chat chat, Boolean isMine) {

        User user = chat.getUser();

        return ChatResponse
                .builder()
                .roomId(chat.getRoom().getId())
                .isMine(isMine)
                .user(UserResponse.of(user))
                .message(chat.getMessage())
                .sentAt(DateUtil.meridiemFormat(chat.getCreatedAt()))
                .build();
    }
}