package com.example.social.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsOfUserWithFriends {

    @JsonProperty("users")
    private UserPosts userPosts;
    @JsonProperty("friends")
    private List<UserPosts> friendPosts;

}
