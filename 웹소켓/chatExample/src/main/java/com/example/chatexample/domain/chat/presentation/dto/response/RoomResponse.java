package com.example.chatexample.domain.chat.presentation.dto.response;

import com.example.chatexample.domain.chat.domain.Room;
import com.example.chatexample.domain.chat.domain.RoomUser;
import com.example.chatexample.domain.chat.domain.enums.RoomType;
import com.example.chatexample.domain.user.domain.User;
import com.example.chatexample.global.util.date.DateUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomResponse {

    private Long roomId;
    private String roomName;
    private String roomLogoImage;
    private RoomType roomType;
    private Boolean isRead;
    private LastChat lastChat;

    public static RoomResponse of(RoomUser roomUser) {

        User user = roomUser.getUser();
        Room room = roomUser.getRoom();

        return RoomResponse
                .builder()
                .roomId(room.getId())
                .roomName(room.getRoomName(user))
                .roomType(room.getRoomType())
                .isRead(room.getLastChat().getLastSentAt().isBefore(roomUser.getLastRead()))
                .lastChat(LastChat
                        .builder()
                        .lastMessage(room.getLastChat().getLastMessage())
                        .lastSentAt(DateUtil.toTimeAgoFormat(room.getLastChat().getLastSentAt()))
                        .build()
                )
                .build();
    }

    @Getter
    @Builder
    public static class LastChat {
        private String lastMessage;
        private String lastSentAt;
    }
}