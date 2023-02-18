package com.example.social.web;

import com.example.social.dto.MLDto;
import com.example.social.services.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/ml")
public class MLController {

    @Autowired
    private TestService service;

    @PostMapping("/save/{email}")
    public void saveData(@PathVariable("email")String email, @RequestBody MLDto mlDtos) {
        service.saveData(email,mlDtos);
    }
}
