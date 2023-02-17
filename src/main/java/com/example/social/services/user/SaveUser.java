package com.example.social.services.user;

import com.example.social.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SaveUser{

    void saveUser(UserDto userDto, MultipartFile file);

    void updateUser(UserDto userDto, MultipartFile file,MultipartFile backgroundImage) throws IOException;

    void saveUser(UserDto userDto);
}
