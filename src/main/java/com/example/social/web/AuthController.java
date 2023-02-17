package com.example.social.web;


import com.example.social.dto.AuthRequestBody;
import com.example.social.dto.AuthResponseBody;
import com.example.social.dto.GoogleResponse;
import com.example.social.services.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public AuthResponseBody validateOAuthTokenAndGenerateJwtToken(@Valid @RequestBody AuthRequestBody authRequestBody) {
        GoogleResponse googleResponse = authService.verifyTokenOfGoogleSSO(authRequestBody);
        authService.extractUsernameVerifiedUser(googleResponse);
        return new AuthResponseBody(googleResponse.getEmail(), googleResponse.getUsername(), authService.generateJwtToken(authService.setUserDetails(googleResponse),googleResponse.getEmail()), googleResponse.isUserExists());
    }
}
