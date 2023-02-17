package com.example.social.services.privateroom;


import com.example.social.dto.PrivateRoomsData;
import com.example.social.mapper.PrivateRoomMapper;
import com.example.social.repo.PrivateRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PrivateRoomServiceClient implements PrivateRoomService {

    @Autowired
    private PrivateRoomRepo repo;

    @Autowired
    private PrivateRoomMapper mapper;

    @Override
    public List<PrivateRoomsData> getPrivateRoomsOfUser(String email) {
        return mapper.mapToData(repo.findPrivateRoomsByUser1EmailOrUser2Email(email,email));
    }
}
