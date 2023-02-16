package com.example.social.services.friend;

import com.example.social.dto.FriendsData;
import com.example.social.entities.Friends;
import com.example.social.mapper.FriendMapper;
import com.example.social.repo.FriendRepo;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class FriendServiceClient implements FriendService {

    @Autowired
    private FriendRepo repo;

    @Autowired
    private FriendMapper mapper;

    @Async(value = "asyncExecutor")
    @Override
    public CompletableFuture<List<Friends>> getFriendsOfUser(String userEmail) {
        return CompletableFuture.completedFuture(repo.findByUserEmail(userEmail));
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

    public List<FriendsData> getFriendsDataOfUser(String email) {
        return mapper.mapToFriendsData(repo.findFriendsDataByUserEmail(email));
    }
}
