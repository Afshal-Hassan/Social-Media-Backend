package com.example.social.services.post;

import com.example.social.cache.CacheProcessor;
import com.example.social.dto.PostData;
import com.example.social.dto.PostsOfUserWithFriends;
import com.example.social.entities.Friends;
import com.example.social.entities.Post;
import com.example.social.entities.User;
import com.example.social.mapper.PostMapper;
import com.example.social.repo.PostRepository;

import com.example.social.services.friend.FriendService;
import com.example.social.services.user.UserService;
import com.example.social.utils.ImageProcessor;
import com.example.social.utils.VideoProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;



@Service
public class PostServiceClient implements PostService{

    private final Logger logger = LoggerFactory.getLogger(PostServiceClient.class);

    @Autowired
    private PostRepository repo;

    @Autowired
    private PostMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private VideoProcessor videoProcessor;
    @Autowired
    private CacheProcessor cacheProcessor;


    @Override
    public void savePost(PostData postData, MultipartFile file) throws IOException {
        boolean isVideoFile = false;
        User user = userService.getUserByEmail(postData.getUserEmail());
        Post post = mapper.mapToEntity(postData);
        post.setUser(user);
        String fileName = file.getContentType();
        String[] fileWords = fileName.split("/");
        for(String fileWord : fileWords)
        {
            if(fileWord.equals("mp4"))
            {
                isVideoFile = true;
            }
        }
        if(isVideoFile)
        {
            String videoPath = videoProcessor.uploadVideo(file);
            post.setVideo(videoPath);
        }
        else if (!file.isEmpty())
        {
            post.setImage(ImageProcessor.uploadImage(file));
        }
        repo.save(post);
        cacheProcessor.refreshAllCache();
    }



    @Override
    @Cacheable(cacheNames = "posts" , key = "#email")
    public PostsOfUserWithFriends getPostsOfUser(String email) throws ExecutionException, InterruptedException {
        List<List<PostData>> friendsPosts = new ArrayList<>();

        CompletableFuture<List<Friends>> friends = friendService.getFriendsOfUser(email);
        CompletableFuture<List<PostData>> userPosts = mapper.mapToPostDataList(repo.findPostsByUserEmail(friends.get().isEmpty() ? null : friends.get().get(0).getUser().getEmail()));

        friends.thenApply(friendsData ->{
            for (Friends friend : friendsData) {
                CompletableFuture<List<PostData>> friendPostFuture = mapper.mapToPostDataList(repo.findPostsByUserEmail(friend.getUserFriends().getEmail()));
                friendPostFuture.thenAccept(friendsPosts::add);
            }
            return friendsPosts;
        });
        return mapper.mapToPostUserWithFriends(userPosts.get(),friendsPosts);
    }



    @Override
    @Cacheable(cacheNames = "posts" , key = "#email")
    public List<PostData> getPosts(String email) throws ExecutionException, InterruptedException {
        List<PostData> postDataList = new ArrayList<>();

        CompletableFuture<List<Friends>> friends = friendService.getFriendsOfUser(email);
        CompletableFuture<List<PostData>> userPosts = mapper.mapToPostDataList(repo.findPostsByUserEmail(friends.get().isEmpty() ? null : friends.get().get(0).getUser().getEmail()));
        userPosts.thenAccept(postDataList::addAll);


        friends.thenApply(friendsData -> {
           for(int i=0; i < friendsData.size() + 1; i++){
                CompletableFuture<List<PostData>> friendPostFuture = mapper.mapToPostDataList(repo.findPostsByUserEmail(friendsData.get(i).getUserFriends().getEmail()));
               friendPostFuture.thenAccept(postDataList::addAll);
            }
           return postDataList;
        });

        return postDataList;
    }
}

