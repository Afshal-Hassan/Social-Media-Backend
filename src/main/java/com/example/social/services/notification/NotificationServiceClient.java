package com.example.social.services.notification;

import com.example.social.dto.NotificationData;
import com.example.social.mapper.NotificationMapper;
import com.example.social.repo.NotificationsRepo;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceClient implements NotificationService{

    @Autowired
    private NotificationsRepo repo;

    @Autowired
    private NotificationMapper mapper;

    @Override
    public List<NotificationData> getNotificationsOfUser(String userEmail) {
        return mapper.mapToData(repo.findNotificationsByNotificationReceiverEmail(userEmail));
    }
}
