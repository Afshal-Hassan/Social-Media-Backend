package com.example.social.entities;

import lombok.*;
import jakarta.persistence.*;


@Entity
@Table(name = "private_rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PrivateRooms {

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "user_1_id" , referencedColumnName = "user_id")
    private User user1;

    @ManyToOne()
    @JoinColumn(name = "user_2_id" , referencedColumnName = "user_id")
    private User user2;
}
