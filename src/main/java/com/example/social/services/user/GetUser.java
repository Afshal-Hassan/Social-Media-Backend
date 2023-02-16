package com.example.social.services.user;

import com.example.social.dto.UserDto;
import com.example.social.entities.User;

import java.util.List;

public interface GetUser {

    User getUserByEmail(String email);

    UserDto getUserDetails(String byUser,String email);

    List<List<UserDto>> getUserDetailsForRecommendation(String byUser,List<String> name);
}
