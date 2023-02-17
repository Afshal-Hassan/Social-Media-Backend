package com.example.social.services.privateroom;

import com.example.social.dto.PrivateRoomsData;

import java.util.List;

public interface GetPrivateRoom {

    List<PrivateRoomsData> getPrivateRoomsOfUser(String email);
}
