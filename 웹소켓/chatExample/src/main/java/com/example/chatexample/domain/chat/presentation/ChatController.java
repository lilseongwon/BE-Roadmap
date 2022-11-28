package com.example.chatexample.domain.chat.presentation;

import com.example.chatexample.domain.chat.presentation.dto.request.CreatePrivateChatRoomRequest;
import com.example.chatexample.domain.chat.presentation.dto.response.CreateRoomResponse;
import com.example.chatexample.domain.chat.presentation.dto.response.QueryChatListResponse;
import com.example.chatexample.domain.chat.presentation.dto.response.QueryRoomListResponse;
import com.example.chatexample.domain.chat.service.CreatePrivateChatRoomService;
import com.example.chatexample.domain.chat.service.QueryChatListService;
import com.example.chatexample.domain.chat.service.QueryMyRoomListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RequestMapping("/chats")
@RestController
public class ChatController {

    private final QueryChatListService queryChatListService;
    private final QueryMyRoomListService queryMyRoomListService;
    private final CreatePrivateChatRoomService createPrivateChatRoomService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/room")
    public CreateRoomResponse createPrivateChatRoom(@RequestBody @Valid CreatePrivateChatRoomRequest request) {
        return createPrivateChatRoomService.execute(request);
    }

    @GetMapping("/{room-id}")
    public QueryChatListResponse queryChatList(@PathVariable("room-id") Long roomId,
                                               @RequestParam("time") LocalDateTime localDateTime) {
        return queryChatListService.execute(roomId, localDateTime);
    }

    @GetMapping("/room")
    public QueryRoomListResponse queryMyRoomList() {
        return queryMyRoomListService.execute();
    }

}