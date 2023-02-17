package com.example.social.mapper;

import com.example.social.dto.UserDto;
import com.example.social.entities.User;
import com.example.social.services.friend.FriendService;
import com.example.social.services.notification.NotificationService;
import jakarta.persistence.Tuple;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private FriendService friendService;

    @Autowired
    private NotificationService notificationService;


    public User mapToEntity(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCountry(userDto.getCountry());
        user.setPhoneNo(userDto.getPhoneNo());
        return user;
    }

    public User mapToEntityForUpdate(UserDto userDto,User user){
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setCountry(userDto.getCountry());
        user.setPhoneNo(userDto.getPhoneNo());
        return user;
    }

    public UserDto mapToUserData(User user, Tuple tuple){
        UserDto userDto = new UserDto();
        userDto.setProfilePic(user.getProfilePic());
        userDto.setBackgroundImage(user.getBackgroundImage());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setCountry(user.getCountry());
        userDto.setPhoneNo(user.getPhoneNo());
        userDto.setFriendsCount(tuple.get(0, Long.class));
        return userDto;
    }

    public List<UserDto> mapToUserData(String email,List<User> users){

        if(users.isEmpty())
        {
            return null;
        }
        else
        {
            List<UserDto> userDtoList = new ArrayList<>();
            for (var index = 0; index < users.size(); index++){

                UserDto userDto = new UserDto();
                userDto.setId(users.get(index).getUserId());
                userDto.setProfilePic(users.get(index).getProfilePic());
                userDto.setBackgroundImage(users.get(index).getBackgroundImage());
                userDto.setPassword(users.get(index).getPassword());
                userDto.setName(users.get(index).getName());
                userDto.setEmail(users.get(index).getEmail());
                userDto.setCountry(users.get(index).getCountry());
                userDto.setPhoneNo(users.get(index).getPhoneNo());
                userDto.setFriend(friendService.isFriendOfUser(email, userDto.getEmail()));
                userDto.setFriendRequestStatus(notificationService.findNotificationStatusOfUser(email,userDto.getEmail()));
                Tuple countsOfFriendOfUser = friendService.getCountsOfFriendOfUser(userDto.getEmail());
                userDto.setFriendsCount(countsOfFriendOfUser.get(0, Long.class));

                userDtoList.add(userDto);
            }
            return userDtoList;
        }
    }
}
