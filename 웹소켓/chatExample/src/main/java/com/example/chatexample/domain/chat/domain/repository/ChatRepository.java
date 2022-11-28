package com.example.chatexample.domain.chat.domain.repository;

import com.example.chatexample.domain.chat.domain.Chat;


import com.example.chatexample.domain.chat.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findTop100ByRoomAndCreatedAtBeforeOrderByIdAsc(Room room, LocalDateTime localDateTime);
}