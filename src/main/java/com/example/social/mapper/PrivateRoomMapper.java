package com.example.social.mapper;


import com.example.social.dto.PrivateRoomsData;
import com.example.social.entities.PrivateRooms;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrivateRoomMapper {

    @Autowired
    private ModelMapper mapper;


    public List<PrivateRoomsData> mapToData(List<PrivateRooms> privateRooms) {
        List<PrivateRoomsData> privateRoomsDataList = new ArrayList<>();

        for (PrivateRooms privateRoom : privateRooms) {
            privateRoomsDataList.add(new PrivateRoomsData(privateRoom.getId()));
        }
        return privateRoomsDataList;
    }
}
