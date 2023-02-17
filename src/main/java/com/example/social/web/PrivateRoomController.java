package com.example.social.web;

import com.example.social.dto.PrivateRoomsData;
import com.example.social.services.privateroom.PrivateRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/private-rooms")
public class PrivateRoomController {

    @Autowired
    private PrivateRoomService service;


    @GetMapping("/list/{email}")
    public List<PrivateRoomsData> getPrivateRoomsOfUser(@PathVariable("email")String email) {
        return service.getPrivateRoomsOfUser(email);
    }
}
