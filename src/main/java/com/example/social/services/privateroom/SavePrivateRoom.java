package com.example.social.services.privateroom;

import com.example.social.entities.User;

import java.util.List;

public interface SavePrivateRoom {

    void savePrivateRoomKeyOfUser(List<User> users,String email);
}
