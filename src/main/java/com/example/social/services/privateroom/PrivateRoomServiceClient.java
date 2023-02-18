package com.example.social.services.privateroom;


import com.example.social.dto.PrivateRoomsData;
import com.example.social.entities.PrivateRooms;
import com.example.social.entities.User;
import com.example.social.mapper.PrivateRoomMapper;
import com.example.social.repo.sm_repos.PrivateRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    @Override
    public void savePrivateRoomKeyOfUser(List<User> users,String userEmail) {
         Optional<User> getUser =  users.stream().filter(user -> user.getEmail().equals(userEmail)).findFirst();
         List<User> filteredUsers = users.stream().filter(user -> !user.getEmail().equals(userEmail)).toList();
         User user = getUser.orElse(null);

         List<PrivateRooms> privateRoomsList = new ArrayList<>();
         if(user != null) {
             for (User filteredUser : filteredUsers) {
                 PrivateRooms privateRooms = new PrivateRooms();
                 privateRooms.setUser2(filteredUser);
                 privateRooms.setUser1(user);

                 privateRoomsList.add(privateRooms);
             }
             repo.saveAll(privateRoomsList);
         }

    }
}
