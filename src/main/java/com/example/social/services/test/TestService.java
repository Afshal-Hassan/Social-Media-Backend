package com.example.social.services.test;

import com.example.social.dto.MLDto;
import com.example.social.entities.ml_domain.Test;

import java.util.List;

public interface TestService {

    void saveData(String email,MLDto data);
}
