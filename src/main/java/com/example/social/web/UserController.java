package com.example.social.web;

import com.example.social.dto.UserDto;
import com.example.social.entities.User;
import com.example.social.services.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


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
    public UserDto getUserForProfile(@PathVariable("byUser")String byUser,@PathVariable("email")String email){
        return service.getUserDetails(byUser,email);
    }


    @PutMapping("/update")
    public String updateUser(@RequestParam("user")String requestBody, @RequestParam(value = "image", required = false)MultipartFile file,
                             @RequestParam(value = "backgroundImage",required = false)MultipartFile backgroundImage) throws IOException {

        UserDto userDto = objectMapper.readValue(requestBody, UserDto.class);
        service.updateUser(userDto,file,backgroundImage);
        return "Updated Successfully";
    }

    @PostMapping("/list/get/{email}")
    public List<List<UserDto>> getUsersForRecommendation(@PathVariable("email")String byUser,@RequestBody List<String> recommendedUsers){
        return service.getUserDetailsForRecommendation(byUser,recommendedUsers);
    }

    @GetMapping("/check/{email}")
    public boolean checkUserExists(@PathVariable("email")String email) {
        User user = service.getUserByEmail(email);
        if(user !=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
