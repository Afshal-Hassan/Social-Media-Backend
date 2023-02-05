package com.example.social.web;

import com.example.social.dto.UserDto;
import com.example.social.services.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;



@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private ObjectMapper objectMapper;


//    @PostMapping("/save")
//    public String saveUser(@RequestParam("user")String requestBody, @RequestParam("image")MultipartFile file) throws IOException {
//        UserDto userDto = objectMapper.readValue(requestBody, UserDto.class);
//        service.saveUser(userDto,file);
//        return "Saved Successfully";
//    }
//
    @GetMapping("/get/{byUser}/{email}")
    public UserDto getUser(@PathVariable("byUser")String byUser,@PathVariable("email")String email){
        return service.getUserDetails(byUser,email);
    }


    @PutMapping("/update")
    public String updateUser(@RequestParam("user")String requestBody, @RequestParam("image")MultipartFile file,
                             @RequestParam("backgroundImage")MultipartFile backgroundImage) throws IOException {

        UserDto userDto = objectMapper.readValue(requestBody, UserDto.class);
        service.updateUser(userDto,file,backgroundImage);
        return "Updated Successfully";
    }
}
