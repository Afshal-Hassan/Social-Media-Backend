package com.example.social.services.test;


import com.example.social.dto.MLDto;
import com.example.social.entities.User;
import com.example.social.entities.ml_domain.Test;
import com.example.social.mapper.MLMapper;
import com.example.social.repo.ml_repos.TestRepo;
import com.example.social.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceClient implements TestService {

    @Autowired
    private TestRepo repo;

    @Autowired
    private UserService userService;

    @Autowired
    private MLMapper mapper;


    @Override
    public void saveData(String email,MLDto data) {
        User user = userService.getUserByEmail(email);
        String name = user.getName();
        repo.saveAll(mapper.mapToEntity(name,data));
    }
}
