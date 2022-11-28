package com.example.chatexample.domain.chat.domain.repository;


import com.example.chatexample.domain.chat.domain.PrivateRoom;
import org.springframework.data.repository.CrudRepository;

public interface PrivateRoomRepository extends CrudRepository<PrivateRoom, PrivateRoom.PrivateRoomId> {
}