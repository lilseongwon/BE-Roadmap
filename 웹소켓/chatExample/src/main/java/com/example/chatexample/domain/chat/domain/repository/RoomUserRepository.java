package com.example.chatexample.domain.chat.domain.repository;


import com.example.chatexample.domain.chat.domain.Room;
import com.example.chatexample.domain.chat.domain.RoomUser;
import com.example.chatexample.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomUserRepository extends CrudRepository<RoomUser, RoomUser.RoomUserId> {

    List<RoomUser> findByUser(User user);
    boolean existsByRoomAndUser(Room room, User user);
}