package com.campuscentral.model;

import com.campuscentral.common.enums.BookingStatus;
import com.campuscentral.util.DateTime;

public class Booking {
    private String bookingId;
    private String bookingReference;
    private String userId;
    private String facilityId;
    private DateTime bookingDate;
    private String startTime;
    private String endTime;
    private int durationHours;
    private BookingStatus bookingStatus;
    private String cancellationReason;
    private String bookingNotes;
    private boolean isReminderEnabled;
    private DateTime createdAt;
    private DateTime updatedAt;

    public Booking() {
        this.bookingId = java.util.UUID.randomUUID().toString();
        this.bookingStatus = BookingStatus.PENDING;
        this.isReminderEnabled = false;
        this.createdAt = new DateTime();
        this.updatedAt = new DateTime();
    }

    public String createBooking() { this.bookingReference = generateBookingReference(); this.bookingStatus = BookingStatus.CONFIRMED; return bookingReference; }
    public boolean confirmBooking() { this.bookingStatus = BookingStatus.CONFIRMED; return true; }
    public boolean cancelBooking() { this.bookingStatus = BookingStatus.CANCELLED; return true; }
    public boolean rescheduleBooking(DateTime newDate, String newStart, String newEnd) {
        this.bookingDate = newDate; this.startTime = newStart; this.endTime = newEnd; return true;
    }
    public int calculateDuration() { return durationHours; }
    public String generateBookingReference() { return "BK-" + System.currentTimeMillis() % 10000; }
    public String getBookingSummary() { return "Ref: " + bookingReference + " | " + bookingDate.formatDate() + " " + startTime + "-" + endTime + " | " + bookingStatus; }
    public String shareBooking() { return getBookingSummary(); }
    public boolean addToCalendar() { return true; }
    public String downloadBookingDetails() { return getBookingSummary(); }

    public String getBookingId() { return bookingId; }
    public String getBookingReference() { return bookingReference; }
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }
    public DateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(DateTime bookingDate) { this.bookingDate = bookingDate; }
    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
    public BookingStatus getBookingStatus() { return bookingStatus; }
    public void setBookingStatus(BookingStatus bookingStatus) { this.bookingStatus = bookingStatus; }
    public void setDurationHours(int durationHours) { this.durationHours = durationHours; }
    public void setBookingNotes(String bookingNotes) { this.bookingNotes = bookingNotes; }
    public boolean isReminderEnabled() { return isReminderEnabled; }
}