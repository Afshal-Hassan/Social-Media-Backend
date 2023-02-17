package com.example.social.services.user;

import com.example.social.dto.UserDto;
import com.example.social.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SaveUser{

    void saveUser(UserDto userDto, MultipartFile file);

    void updateUser(UserDto userDto, MultipartFile file,MultipartFile backgroundImage) throws IOException;

    void saveUser(User user);
}
