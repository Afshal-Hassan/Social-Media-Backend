package com.example.social.mapper;

import com.example.social.dto.NotificationData;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationMapper {

    @Autowired
    private ModelMapper mapper;

    public List<NotificationData> mapToData(List<Tuple> notifications) {
        List<NotificationData> notificationDataList = new ArrayList<>();

        for (Tuple notification : notifications) {

            NotificationData notificationData = new NotificationData();

            notificationData.setId(notification.get(0, Integer.class));
            notificationData.setNotification(notification.get(1, String.class));
            notificationData.setNotificationSenderName(notification.get(2, String.class));
            notificationData.setNotificationSenderEmail(notification.get(3, String.class));
            notificationData.setNotificationSenderProfilePic(notification.get(4, String.class));
            notificationData.setNotificationReceiverName(notification.get(5, String.class));
            notificationData.setNotificationReceiverEmail(notification.get(6, String.class));
            notificationData.setNotificationReceiverProfilePic(notification.get(7, String.class));

            notificationDataList.add(notificationData);
        }
        return notificationDataList;
    }
}
