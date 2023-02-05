package com.example.social.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostData {

    @JsonProperty("postDescription")
    private String description;

    @JsonProperty("postImage")
    private String image;

    @JsonProperty("video")
    private String video;

    @JsonProperty("createdAt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-M-d'T'H:m:s")
    private LocalDateTime createdAt;

    @JsonProperty("likes")
    private Long likes;

    @JsonProperty("hearts")
    private Long hearts;

    @JsonProperty("userEmail")
    private String userEmail;
}
