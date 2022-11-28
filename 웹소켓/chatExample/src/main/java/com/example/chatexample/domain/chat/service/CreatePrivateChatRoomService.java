package com.example.chatexample.domain.chat.service;

import com.example.chatexample.domain.chat.domain.PrivateRoom;
import com.example.chatexample.domain.chat.domain.Room;
import com.example.chatexample.domain.chat.domain.enums.RoomType;
import com.example.chatexample.domain.chat.domain.repository.PrivateRoomRepository;
import com.example.chatexample.domain.chat.domain.repository.RoomRepository;
import com.example.chatexample.domain.chat.facade.RoomFacade;
import com.example.chatexample.domain.chat.presentation.dto.request.CreatePrivateChatRoomRequest;
import com.example.chatexample.domain.chat.presentation.dto.response.CreateRoomResponse;
import com.example.chatexample.domain.user.domain.User;
import com.example.chatexample.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreatePrivateChatRoomService {

    private final RoomRepository roomRepository;
    private final PrivateRoomRepository privateRoomRepository;
    private final RoomFacade roomFacade;
    private final UserFacade userFacade;

    @Transactional
    public CreateRoomResponse execute(CreatePrivateChatRoomRequest request) {

        User userA = userFacade.getCurrentUser();
        User userB = userFacade.getUserById(request.getUserId());

        roomFacade.checkPrivateRoomExist(userA, userB);

        Room room = roomRepository.save(Room
                .builder()
                .roomType(RoomType.PRIVATE)
                .build()
        );

        room.addRoomUser(userA);
        room.addRoomUser(userB);

        privateRoomRepository.save(new PrivateRoom(userA, userB, room));

        return new CreateRoomResponse(room.getId());
    }
}