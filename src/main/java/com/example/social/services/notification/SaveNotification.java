package com.example.social.services.notification;

import com.example.social.dto.NotificationData;
import com.example.social.dto.NotificationPayload;

public interface SaveNotification {

    String saveNotification(NotificationPayload notificationPayload);

    String updateNotification(String senderEmail,String receiverEmail,NotificationPayload notificationPayload);
}
