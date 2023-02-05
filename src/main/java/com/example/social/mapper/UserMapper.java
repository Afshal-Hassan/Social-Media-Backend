package com.example.social.mapper;

import com.example.social.dto.UserDto;
import com.example.social.dto.UserPosts;
import com.example.social.entities.User;
import com.example.social.utils.ImageProcessor;
import jakarta.persistence.Tuple;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper mapper;


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
        user.setEmail(userDto.getEmail());
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
}
