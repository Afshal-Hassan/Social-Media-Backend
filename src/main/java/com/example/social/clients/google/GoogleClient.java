package com.example.social.clients.google;

import com.example.social.dto.AuthRequestBody;
import com.example.social.dto.GoogleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class GoogleClient {

    @Autowired
    private RestTemplate restTemplate;


    public GoogleResponse verifyTokenFromGoogle(AuthRequestBody authRequestBody){

        try{
            return restTemplate.getForObject("https://oauth2.googleapis.com/tokeninfo?scope=email&access_token=" + authRequestBody.getAccessToken(), GoogleResponse.class);

        } catch (Exception ex){
            ex.getCause();
            throw new RuntimeException("Unable to verify from google");

        }
    }
}
