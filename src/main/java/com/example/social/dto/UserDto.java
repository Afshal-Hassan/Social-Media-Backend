package com.example.social.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("country")
    private String country;

    @JsonProperty("phoneNo")
    private String phoneNo;

    @JsonProperty("friendsCount")
    private Long friendsCount;

    @JsonProperty("accountOwnership")
    private boolean accountOwnership;

    @JsonProperty("isFriend")
    private boolean isFriend;

    @JsonProperty("profilePic")
    private String profilePic;

    @JsonProperty("backgroundImage")
    private String backgroundImage;
}
