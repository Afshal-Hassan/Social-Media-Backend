package com.example.social.services.post;

import com.example.social.dto.PostData;
import com.example.social.dto.PostsOfUserWithFriends;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface GetPost {

    PostsOfUserWithFriends getPostsOfUser(String email) throws ExecutionException, InterruptedException;

    List<PostData> getPosts(String email) throws ExecutionException, InterruptedException;
}
