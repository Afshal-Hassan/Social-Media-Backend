package com.example.social.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;

    @Column(name = "post_description")
    private String description;

    @Column(name = "post_image")
    private String image;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "likes")
    private long likes;

    @Column(name = "hearts")
    private long hearts;

    @Column(name = "video")
    private String video;

    @ManyToOne()
    @JoinColumn(name = "user_id" , referencedColumnName = "user_id")
    private User user;
}
