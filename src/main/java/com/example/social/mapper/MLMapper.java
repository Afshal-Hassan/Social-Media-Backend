package com.example.social.mapper;

import com.example.social.dto.MLDto;
import com.example.social.entities.ml_domain.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MLMapper {

    @Autowired
    private ModelMapper mapper;


    public List<Test> mapToEntity(String name,MLDto data) {
        List<String> interests = data.getInterests();

        return interests.stream().map(interest -> {
            Test test = new Test();
            test.setInterest(interest);
            test.setCategory(interest+" Category");
            test.setUser(name);
            return test;
        }).toList();
    }
}
