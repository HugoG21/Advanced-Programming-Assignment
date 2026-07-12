package com.campuscentral.service;

import com.campuscentral.model.Notification;
import com.campuscentral.common.enums.NotificationType;
import com.campuscentral.common.enums.ReminderType;
import com.campuscentral.util.DateTime;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private final List<Notification> notifications;

    public NotificationService() {
        this.notifications = new ArrayList<>();
    }

    public Notification createNotification(String userId, String title, String message, NotificationType type, ReminderType channel) {
        Notification n = new Notification();
        n.setUserId(userId);
        n.setTitle(title);
        n.setMessage(message);
        n.setNotificationType(type);
        n.setDeliveryChannel(channel);
        n.sendNotification();
        notifications.add(n);
        return n;
    }

    public boolean sendNotification(Notification n) { return n.sendNotification(); }
    public boolean sendEmailReminder(String email, String message) { return true; }
    public boolean sendSMSReminder(String phone, String message) { return true; }
    public boolean markAsRead(String notificationId) {
        return notifications.stream()
                .filter(n -> n.getNotificationId().equals(notificationId))
                .findFirst()
                .map(Notification::markAsRead)
                .orElse(false);
    }
    public boolean deleteNotification(String notificationId) {
        return notifications.removeIf(n -> n.getNotificationId().equals(notificationId));
    }
    public boolean resendNotification(String notificationId) {
        return notifications.stream()
                .filter(n -> n.getNotificationId().equals(notificationId))
                .findFirst()
                .map(Notification::resendNotification)
                .orElse(false);
    }
    public List<Notification> getUserNotifications(String userId) {
        return notifications.stream().filter(n -> n.getUserId().equals(userId)).toList();
    }
}