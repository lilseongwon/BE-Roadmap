package com.example.chatexample.domain.chat.domain;

import com.example.chatexample.domain.chat.domain.enums.RoomType;
import com.example.chatexample.domain.user.domain.User;
import com.example.chatexample.domain.user.exception.UserNotFoundException;
import com.example.chatexample.global.util.date.DateUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column(nullable = false)
    private RoomType roomType;

    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<RoomUser> roomUsers = new ArrayList<>();

    @OneToOne(mappedBy = "room")
    private PrivateRoom privateRoom;

    @Embedded
    @Column(nullable = false)
    private LastChat lastChat;

    public String getRoomName(User user) {

            try{
                return getOtherUser(user).getName();
            } catch (UserNotFoundException e) {
                return "알 수 없는 유저";
            }
        }

    private User getOtherUser(User user) {
        if(roomUsers.size() < 2){
            throw UserNotFoundException.EXCEPTION;
        }
        User user1 = roomUsers.get(0).getUser();
        User user2 = roomUsers.get(1).getUser();
        return user1 != user ? user1 : user2;
    }

    public void addRoomUser(User user) {
        this.roomUsers.add(RoomUser
                .builder()
                .room(this)
                .user(user)
                .build());
    }

    @Builder
    public Room(RoomType roomType, PrivateRoom privateRoom) {
        this.roomType = roomType;
        this.privateRoom = privateRoom;
        this.lastChat = new LastChat();
    }

    public void updateLastMessage(Chat chat) {
        this.lastChat.lastMessage = chat.getMessage();
        this.lastChat.lastSentAt = chat.getCreatedAt();
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Embeddable
    public static class LastChat {
        private String lastMessage = "";
        private LocalDateTime lastSentAt = DateUtil.getZonedNow();
    }
}