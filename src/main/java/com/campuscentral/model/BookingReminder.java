package com.campuscentral.model;

import com.campuscentral.common.enums.ReminderType;
import com.campuscentral.util.DateTime;

public class BookingReminder {
    private String reminderId;
    private String bookingId;
    private ReminderType reminderType;
    private String message;
    private DateTime scheduledTime;
    private DateTime sentAt;
    private boolean isSent;
    private DateTime createdAt;
    private DateTime updatedAt;

    public BookingReminder() {
        this.reminderId = java.util.UUID.randomUUID().toString();
        this.isSent = false;
        this.createdAt = new DateTime();
        this.updatedAt = new DateTime();
    }

    public boolean scheduleReminder() { return true; }
    public boolean sendReminder() { this.isSent = true; this.sentAt = new DateTime(); return true; }
    public boolean cancelReminder() { return true; }
    public boolean updateReminderTime(DateTime newTime) { this.scheduledTime = newTime; return true; }
    public boolean markAsSent() { this.isSent = true; return true; }
    public String generateReminderMessage() { return "Reminder: " + message; }

    public String getReminderId() { return reminderId; }
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public ReminderType getReminderType() { return reminderType; }
    public void setReminderType(ReminderType reminderType) { this.reminderType = reminderType; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public DateTime getScheduledTime() { return scheduledTime; }
    public void setScheduledTime(DateTime scheduledTime) { this.scheduledTime = scheduledTime; }
    public boolean isSent() { return isSent; }
}