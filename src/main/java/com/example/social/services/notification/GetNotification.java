package com.example.social.services.notification;

import com.example.social.dto.NotificationData;
import jakarta.persistence.Tuple;
import java.util.List;



public interface GetNotification {

    List<NotificationData> getNotificationsOfUser(String email);

    String findNotificationStatusOfUser(String byUser,String ofUser);
}
