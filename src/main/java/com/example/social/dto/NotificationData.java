package com.example.social.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationData {

    @JsonProperty("notificationID")
    private int id;

    @JsonProperty("notification")
    private String notification;

    @JsonProperty("notificationSenderName")
    private String notificationSenderName;

    @JsonProperty("notificationReceiverName")
    private String notificationReceiverName;

    @JsonProperty("notificationSenderEmail")
    private String notificationSenderEmail;

    @JsonProperty("notificationReceiverEmail")
    private String notificationReceiverEmail;

    @JsonProperty("notificationSenderProfilePic")
    private String notificationSenderProfilePic;

    @JsonProperty("notificationReceiverProfilePic")
    private String notificationReceiverProfilePic;
}
