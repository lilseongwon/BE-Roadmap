package com.example.chatexample.domain.chat.domain;

import com.example.chatexample.domain.user.domain.User;
import com.example.chatexample.global.util.date.DateUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(RoomUser.RoomUserId.class)
@Entity
public class RoomUser {

    @Id
    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDateTime lastRead = DateUtil.getZonedNow();

    @Builder
    public RoomUser(Room room, User user) {
        this.room = room;
        this.user = user;
    }

    public void updateLastReadTime() {
        this.lastRead = DateUtil.getZonedNow();
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @EqualsAndHashCode
    public static class RoomUserId implements Serializable {
        private Long room;
        private Long user;
    }

}