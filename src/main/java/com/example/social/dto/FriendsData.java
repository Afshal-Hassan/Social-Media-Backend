package com.example.social.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Tuple;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendsData {

    @JsonProperty("friendId")
    private Integer id;

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

    @JsonProperty("isFriend")
    private boolean isFriend;

    @JsonProperty("friendsCount")
    private Long friendsCount;

    @JsonProperty("profilePic")
    private String profilePic;

    @JsonProperty("backgroundImage")
    private String backgroundImage;

    public Long getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(Tuple friendsCount) {
        this.friendsCount = friendsCount.get(0, Long.class);
    }
}
