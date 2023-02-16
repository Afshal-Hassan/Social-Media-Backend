package com.example.social.services.notification;

import com.example.social.dto.NotificationData;
import com.example.social.dto.NotificationPayload;
import com.example.social.entities.Notifications;
import com.example.social.entities.User;
import com.example.social.mapper.NotificationMapper;
import com.example.social.repo.NotificationsRepo;
import com.example.social.services.friend.FriendService;
import com.example.social.services.user.UserService;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceClient implements NotificationService{

    @Autowired
    private NotificationsRepo repo;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private NotificationMapper mapper;



    @Override
    public List<NotificationData> getNotificationsOfUser(String userEmail) {
        return mapper.mapToData(repo.findNotificationsByNotificationReceiverEmail(userEmail));
    }

    @Override
    public String findNotificationStatusOfUser(String byUser,String ofUser) {
        Tuple tuple = repo.findNotificationStatusOfUser(byUser,ofUser);
        if(tuple != null)
        {
            return tuple.get(0, String.class);
        }
        else
        {
            return null;
        }
    }

    @Override
    public String saveNotification(NotificationPayload notificationPayload) {
        User senderUser = userService.getUserByEmail(notificationPayload.getNotificationSenderEmail());
        User receiverUser = userService.getUserByEmail(notificationPayload.getFriendRequestReceiver());
        Notifications notifications =  mapper.mapToEntity(notificationPayload);
        notifications.setNotificationSender(senderUser);
        notifications.setNotificationReceiver(receiverUser);
        repo.save(notifications);
        return "Notification Saved Successfully";
    }

    @Override
    public String updateNotification(String senderEmail,String receiverEmail,NotificationPayload notificationPayload) {
        Notifications notifications = repo.findByNotificationSenderEmailAndAndNotificationReceiverEmailAndNotificationStatus(senderEmail,receiverEmail,"Pending");
        notifications.setNotificationStatus("Accepted");
        repo.save(notifications);
        User senderUser = userService.getUserByEmail(senderEmail);
        User receiverUser = userService.getUserByEmail(receiverEmail);
        friendService.saveFriends(senderUser,receiverUser);
        Notifications newNotification = mapper.mapToEntity(notificationPayload);
        newNotification.setNotificationSender(receiverUser);
        newNotification.setNotificationReceiver(senderUser);
        repo.save(newNotification);
        return "Updated Successfully";
    }
}
