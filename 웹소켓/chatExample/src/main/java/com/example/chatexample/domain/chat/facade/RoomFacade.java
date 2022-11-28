package com.example.chatexample.domain.chat.facade;

import com.corundumstudio.socketio.SocketIOClient;
import com.example.chatexample.domain.chat.domain.PrivateRoom;
import com.example.chatexample.domain.chat.domain.Room;
import com.example.chatexample.domain.chat.domain.repository.PrivateRoomRepository;
import com.example.chatexample.domain.chat.domain.repository.RoomRepository;
import com.example.chatexample.domain.chat.exception.RoomAlreadyExistException;
import com.example.chatexample.domain.chat.exception.RoomNotFoundException;
import com.example.chatexample.domain.user.domain.User;
import com.example.chatexample.global.socket.util.SocketUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RoomFacade {

    private final RoomRepository roomRepository;
    private final PrivateRoomRepository privateRoomRepository;

    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> RoomNotFoundException.EXCEPTION);
    }

    public Room getCurrentRoom(SocketIOClient socketIOClient) {
        return getRoomById(SocketUtil.getRoomId(socketIOClient));
    }

    public void checkPrivateRoomExist(User userA, User userB) {

        Optional<PrivateRoom> optionalPrivateRoom = privateRoomRepository
                .findById(new PrivateRoom.PrivateRoomId(userA.getId(), userB.getId()));

        if (optionalPrivateRoom.isPresent()){
            throw RoomAlreadyExistException.EXCEPTION;
        }
    }
}