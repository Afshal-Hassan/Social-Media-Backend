package com.example.social.web;

import com.example.social.dto.NotificationData;
import com.example.social.dto.NotificationPayload;
import com.example.social.services.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class NotificationsController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private NotificationService service;


    @MessageMapping("/send-notification")
    public void sendNotificationToUser(@Payload NotificationPayload notificationPayload) {
        messagingTemplate.convertAndSendToUser(notificationPayload.getRoomId(),"/send/private", notificationPayload);
    }

    @MessageMapping("/receive-notification")
    public void receiveNotificationToUser(@Payload NotificationPayload notificationPayload) {
        messagingTemplate.convertAndSendToUser(notificationPayload.getRoomId(),"/receive/private", notificationPayload);
    }



    @GetMapping("/notifications/get/{email}")
    public List<NotificationData> getNotificationsOfUser(@PathVariable("email")String userEmail) {
        return service.getNotificationsOfUser(userEmail);
    }
}
