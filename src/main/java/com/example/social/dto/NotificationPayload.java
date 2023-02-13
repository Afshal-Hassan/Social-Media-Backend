package com.example.social.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationPayload {

    private String friendRequestSender;
    private String message;
    private String roomId;
    private String friendRequestReceiver;
}
