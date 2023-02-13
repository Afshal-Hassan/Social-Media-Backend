package com.example.social.services.notification;

import com.example.social.dto.NotificationData;

import java.util.List;

public interface GetNotification {

    List<NotificationData> getNotificationsOfUser(String email);
}
