package com.example.social.mapper;

import com.example.social.dto.PostData;
import com.example.social.dto.PostsOfUserWithFriends;
import com.example.social.dto.UserPosts;
import com.example.social.entities.Post;
import com.example.social.utils.ImageProcessor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Component
public class PostMapper {

    @Autowired
    private ModelMapper mapper;
    @Lazy
    @Autowired
    private PostMapper postMapper;

    public Post mapToEntity(PostData postData){
        Post post = new Post();
        post.setDescription(postData.getDescription());
        post.setImage(post.getImage());
        post.setCreatedAt(postData.getCreatedAt());
        post.setLikes(postData.getLikes());
        post.setHearts(postData.getHearts());
        return post;
    }
    @Async(value = "asyncExecutor")
    public CompletableFuture<List<PostData>> mapToPostDataList(List<Post> posts) {
        return CompletableFuture.completedFuture(posts.stream().map(post -> mapper.map(post,PostData.class)).toList());
    }

    public UserPosts mapToUserPosts(List<PostData> postData){
        UserPosts userPosts = new UserPosts();
        if( !postData.isEmpty() ) {
            userPosts.setEmail(postData.get(0).getUserEmail());
            userPosts.setPostData(postData);
            return userPosts;
        }
        return null;
    }

    public List<UserPosts> mapToFriendPosts(List<List<PostData>> friendPosts){

        UserPosts userPost = new UserPosts();
        List<UserPosts> userPosts = new ArrayList<>();

        for (List<PostData> friendPost : friendPosts) {
            if(!friendPost.isEmpty()) {
                userPost.setEmail(friendPost.get(0).getUserEmail());
                userPost.setPostData(friendPost);
                userPosts.add(userPost);
            }
        }
        return userPosts;

    }

    public PostsOfUserWithFriends mapToPostUserWithFriends(List<PostData> userPosts, List<List<PostData>> friendPosts){
        PostsOfUserWithFriends posts = new PostsOfUserWithFriends();
        posts.setUserPosts(postMapper.mapToUserPosts(userPosts));
        posts.setFriendPosts(postMapper.mapToFriendPosts(friendPosts));
        return posts;
    }
}
