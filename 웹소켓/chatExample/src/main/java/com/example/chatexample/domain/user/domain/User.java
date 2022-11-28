package com.example.chatexample.domain.user.domain;


import com.example.chatexample.domain.chat.domain.PrivateRoom;
import com.example.chatexample.domain.chat.domain.RoomUser;
import com.example.chatexample.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@BatchSize(size = 500)
@Entity
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 40, unique = true)
    private String email;

    @NotNull
    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 60, nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<RoomUser> roomUsers;

    @OneToMany(mappedBy = "user1", cascade = CascadeType.REMOVE)
    private List<PrivateRoom> privateRooms1;

    @OneToMany(mappedBy = "user2", cascade = CascadeType.REMOVE)
    private List<PrivateRoom> privateRooms2;

    @Builder
    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateInfo(UpdateUserInfoRequest request) {
        this.name = request.getName();
    }

}