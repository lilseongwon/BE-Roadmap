package com.example.chatexample.domain.chat.domain.repository;


import com.example.chatexample.domain.chat.domain.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {
}