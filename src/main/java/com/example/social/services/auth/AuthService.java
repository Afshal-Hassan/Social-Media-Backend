package com.example.social.services.auth;


import com.example.social.dto.AuthRequestBody;
import com.example.social.dto.GoogleResponse;

import java.util.HashMap;
import java.util.Map;

public interface AuthService {

    GoogleResponse verifyTokenOfGoogleSSO(AuthRequestBody authRequestBody);

    HashMap<String,Object> setUserDetails(GoogleResponse googleResponse);

    String generateJwtToken(Map<String, Object> userData, String subject);

    String getUserEmailFromToken(String token);
    void extractUsernameVerifiedUser(GoogleResponse googleResponse);
}
