package com.example.social.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "notification")
    private String notifications;

    @Column(name = "notification_status")
    private String notificationStatus;

    @ManyToOne()
    @JoinColumn(name = "notification_sender" , referencedColumnName = "user_id")
    private User notificationSender;

    @ManyToOne()
    @JoinColumn(name = "notification_receiver" , referencedColumnName = "user_id")
    private User notificationReceiver;
}
