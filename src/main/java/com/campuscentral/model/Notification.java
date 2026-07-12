package com.campuscentral.model;

import com.campuscentral.common.enums.NotificationType;
import com.campuscentral.common.enums.ReminderType;
import com.campuscentral.util.DateTime;

public class Notification {
    private String notificationId;
    private String userId;
    private String title;
    private String message;
    private NotificationType notificationType;
    private ReminderType deliveryChannel;
    private boolean isRead;
    private DateTime sentAt;
    private DateTime createdAt;

    public Notification() {
        this.notificationId = java.util.UUID.randomUUID().toString();
        this.isRead = false;
        this.createdAt = new DateTime();
    }

    public boolean sendNotification() { this.sentAt = new DateTime(); return true; }
    public boolean markAsRead() { this.isRead = true; return true; }
    public boolean markAsUnread() { this.isRead = false; return true; }
    public boolean archiveNotification() { return true; }
    public boolean deleteNotification() { return true; }
    public String getNotificationDetails() { return "[" + notificationType + "] " + title + ": " + message; }
    public boolean resendNotification() { this.sentAt = new DateTime(); return true; }

    public String getNotificationId() { return notificationId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public NotificationType getNotificationType() { return notificationType; }
    public void setNotificationType(NotificationType notificationType) { this.notificationType = notificationType; }
    public ReminderType getDeliveryChannel() { return deliveryChannel; }
    public boolean isRead() { return isRead; }
}