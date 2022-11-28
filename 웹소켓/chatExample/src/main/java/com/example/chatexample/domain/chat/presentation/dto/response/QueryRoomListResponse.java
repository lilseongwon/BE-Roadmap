package com.example.chatexample.domain.chat.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryRoomListResponse {

    private List<RoomResponse> roomList;

}