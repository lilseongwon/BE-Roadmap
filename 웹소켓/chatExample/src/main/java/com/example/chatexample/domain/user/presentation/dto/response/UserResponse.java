package com.example.chatexample.domain.user.presentation.dto.response;

import com.example.chatexample.domain.user.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserResponse {

    @JsonProperty("user_id")
    private final Long userId;

    @JsonProperty("user_name")
    private final String userName;

    public static UserResponse of(User user) {

        return UserResponse
                .builder()
                .userId(user.getId())
                .userName(user.getName())
                .build();
    }
}