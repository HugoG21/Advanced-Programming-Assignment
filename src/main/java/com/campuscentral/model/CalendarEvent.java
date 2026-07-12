package com.campuscentral.model;

import com.campuscentral.common.enums.BookingStatus;
import com.campuscentral.util.DateTime;

public class CalendarEvent {
    private String eventId;
    private String bookingId;
    private String userId;
    private String title;
    private String description;
    private DateTime eventDate;
    private String startTime;
    private String endTime;
    private String location;
    private BookingStatus status;
    private DateTime createdAt;
    private DateTime updatedAt;

    public CalendarEvent() {
        this.eventId = java.util.UUID.randomUUID().toString();
        this.createdAt = new DateTime();
        this.updatedAt = new DateTime();
    }

    public boolean createEvent() { return true; }
    public boolean updateEvent() { return true; }
    public boolean deleteEvent() { return true; }
    public boolean syncBooking() { return true; }
    public String exportToGoogleCalendar() { return "BEGIN:VCALENDAR..."; }
    public String exportToOutlookCalendar() { return "BEGIN:VCALENDAR..."; }
    public String generateCalendarInvite() { return "Calendar invite for " + title; }

    public String getEventId() { return eventId; }
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public DateTime getEventDate() { return eventDate; }
    public void setEventDate(DateTime eventDate) { this.eventDate = eventDate; }
    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
    public String getLocation() { return location; }
}