package com.example.chatexample.domain.chat.presentation.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
public class CreatePrivateChatRoomRequest {

    @NotNull(message = "user_id는 null이어선 안됩니다")
    private Long userId;
}