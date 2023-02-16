package com.example.social.web;

import com.example.social.dto.FriendsData;
import com.example.social.entities.Friends;
import com.example.social.services.friend.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    private FriendService friendService;


    @GetMapping("/list/{email}")
    public List<FriendsData> getFriendsDataOfUser(@PathVariable("email")String email) {
        return friendService.getFriendsDataOfUser(email);
    }
}
