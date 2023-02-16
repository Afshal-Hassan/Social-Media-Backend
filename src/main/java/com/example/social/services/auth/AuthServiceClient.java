package com.example.social.services.auth;


import com.example.social.clients.google.GoogleClient;
import com.example.social.dto.AuthRequestBody;
import com.example.social.dto.GoogleResponse;
import com.example.social.entities.User;
import com.example.social.repo.UserRepo;
import com.example.social.services.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.function.Function;


@Service
public class AuthServiceClient implements AuthService{

    @Autowired
    private GoogleClient googleClient;

    @Autowired
    private UserService userService;

    @Value("${JWT_TOKEN_SECRET}")
    private String SECRET_KEY;



    public GoogleResponse verifyTokenOfGoogleSSO(AuthRequestBody authRequestBody){
        return googleClient.verifyTokenFromGoogle(authRequestBody);
    }

    public void extractUsernameVerifiedUser(GoogleResponse googleResponse) {
        User user = userService.getUserByEmail(googleResponse.getEmail());
        googleResponse.setUsername(user.getName());
    }

    public HashMap<String,Object> setUserDetails(GoogleResponse googleResponse){

        HashMap<String, Object> userData = new HashMap<>();
        userData.put("name", googleResponse.getEmail());
        userData.put("role", "ROLE_USER");
        return userData;
    }


    public String generateJwtToken(Map<String, Object> userData, String subject) {
        return generateJwtToken( userData,
                subject,
                new Date(),
                new Date(System.currentTimeMillis() * 1000) );
    }

    public String generateJwtToken(Map<String, Object> userData,
                                   String subject,
                                   Date issuedDate,
                                   Date expiryDate) {
        return Jwts.builder()
                .setSubject(subject)
                .claim("userDetails", userData)
                .setIssuedAt(issuedDate)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    public String getUserEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
}
