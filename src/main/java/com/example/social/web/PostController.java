package com.example.social.web;

import com.example.social.dto.PostData;
import com.example.social.dto.PostsOfUserWithFriends;
import com.example.social.services.post.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;



@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping(value = "/save")
    public String savePost(@RequestParam("post") String requestBody, @RequestParam("image")MultipartFile file) throws IOException {
        PostData postData = objectMapper.readValue(requestBody, PostData.class);
        postService.savePost(postData,file);
        return "Saved Successfully";
    }


    @GetMapping("/get/of-user/{email}")
    public PostsOfUserWithFriends getPostsOfUser(@PathVariable("email")String email) throws ExecutionException, InterruptedException {
        return postService.getPostsOfUser(email);
    }


    @GetMapping("/get/{email}")
    public List<PostData> getPosts(@PathVariable("email")String email) throws ExecutionException, InterruptedException {
        return postService.getPosts(email);
    }
}
