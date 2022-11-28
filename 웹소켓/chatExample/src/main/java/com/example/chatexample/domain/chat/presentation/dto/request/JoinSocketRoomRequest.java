package com.example.chatexample.domain.chat.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
public class JoinSocketRoomRequest {

    @JsonProperty("is_join_room")
    @NotNull(message = "is_join_room은 null이어선 안됩니다")
    private Boolean isJoinRoom;

    @JsonProperty("room_id")
    @NotNull(message = "room_id는 null이어선 안됩니다")
    private Long roomId;
}