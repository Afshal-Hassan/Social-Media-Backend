package com.example.social.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "friends")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private int friendId;

    @ManyToOne()
    @JoinColumn(name = "user_friend_id", referencedColumnName = "user_id")
    private User userFriends;

    @ManyToOne()
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;


}
