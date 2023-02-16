package com.example.social.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationPayload {

    private int id;
    private String notificationSenderName;
    private String notification;
    private String notificationSenderEmail;
    private String notificationSenderProfilePic;
    private String notificationStatus;
    private String friendRequestReceiver;
    private String roomId;
}
