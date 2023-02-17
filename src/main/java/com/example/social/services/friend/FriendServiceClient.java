package com.example.social.services.friend;

import com.example.social.dto.FriendsData;
import com.example.social.entities.Friends;
import com.example.social.entities.User;
import com.example.social.mapper.FriendMapper;
import com.example.social.repo.FriendRepo;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FriendServiceClient implements FriendService {

    @Autowired
    private FriendRepo repo;

    @Autowired
    private FriendMapper mapper;

    @Override
    public List<Friends> getFriendsOfUser(String userEmail) {
        return repo.findByUserEmail(userEmail);
    }

    @Override
    public Tuple getCountsOfFriendOfUser(String email) {
        return repo.findCountsOfFriendOfUser(email);
    }

    @Override
    public boolean isFriendOfUser(String byUser, String onUser) {
        Tuple tuple = repo.findFriendOfUser(byUser,onUser);
        return tuple != null;
    }

    @Override
    public List<FriendsData> getFriendsDataOfUser(String email) {
        return mapper.mapToFriendsData(repo.findFriendsDataByUserEmail(email));
    }

    @Override
    public void saveFriends(User user1, User user2) {
        Friends friendshipForUser1 = new Friends();
        Friends friendshipForUser2 = new Friends();
        friendshipForUser1.setUser(user1);
        friendshipForUser1.setUserFriends(user2);
        friendshipForUser2.setUser(user2);
        friendshipForUser2.setUserFriends(user1);
        repo.save(friendshipForUser1);
        repo.save(friendshipForUser2);
    }
}
