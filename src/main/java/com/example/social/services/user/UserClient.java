package com.example.social.services.user;

import com.example.social.dto.UserDto;
import com.example.social.entities.User;
import com.example.social.mapper.UserMapper;
import com.example.social.repo.UserRepo;
import com.example.social.services.friend.FriendService;
import com.example.social.utils.ImageProcessor;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserClient implements UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private FriendService friendService;


    @Override
    public void saveUser(UserDto userDto, MultipartFile file) {
        User user = mapper.mapToEntity(userDto);
        repo.save(user);
    }


    @Override
    public User getUserByEmail(String email) {
        User user = repo.findUserByEmail(email);
        return user;
    }


    @Override
    public UserDto getUserDetails(String byUser,String email) {

        User user = repo.findUserByEmail(email);

        Tuple tuple = friendService.getCountsOfFriendOfUser(email);

        UserDto userDto = mapper.mapToUserData(user,tuple);
        // check Acccount Ownership
        if(byUser.equals(email))
        {
            userDto.setAccountOwnership(true);
            userDto.setFriend(false);
        }
        else
        {
            userDto.setAccountOwnership(false);
            userDto.setFriend(friendService.isFriendOfUser(byUser,email));
        }

        return userDto;
    }


    @Override
    public void updateUser(UserDto userDto, MultipartFile file,MultipartFile backgroundImage) throws IOException {
        User getUser = repo.findUserByEmail(userDto.getEmail());
        User user = mapper.mapToEntityForUpdate(userDto,getUser);
        if(file != null)
        {
            if(!file.isEmpty())
            {
                user.setProfilePic(ImageProcessor.uploadImage(file));

            }
        }

        if(backgroundImage != null)
        {
            if(!backgroundImage.isEmpty())
            {
                user.setBackgroundImage(ImageProcessor.uploadImage(backgroundImage));
            }
        }
        repo.save(user);
    }

    @Override
    public List<List<UserDto>> getUserDetailsForRecommendation(String byUser,List<String> recommendedUsers) {
        return recommendedUsers.stream().map(user -> mapper.mapToUserData(byUser,repo.findByName(user))).toList();
    }

    @Override
    public void saveUser(User user) {
        repo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }
}
