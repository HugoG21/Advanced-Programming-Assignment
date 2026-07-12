package com.campuscentral.model;

import com.campuscentral.util.DateTime;

public class BookingPolicy {
    private String policyId;
    private String policyName;
    private int maxAdvanceBookingDays;
    private int maxBookingDurationHours;
    private int cancellationWindowHours;
    private int dailyBookingLimit;
    private boolean allowReschedule;
    private String description;
    private DateTime createdAt;
    private DateTime updatedAt;

    public BookingPolicy() {
        this.policyId = java.util.UUID.randomUUID().toString();
        this.createdAt = new DateTime();
        this.updatedAt = new DateTime();
    }

    public BookingPolicy(String policyName, int maxAdvanceDays, int maxDuration, int cancelWindow, int dailyLimit) {
        this();
        this.policyName = policyName;
        this.maxAdvanceBookingDays = maxAdvanceDays;
        this.maxBookingDurationHours = maxDuration;
        this.cancellationWindowHours = cancelWindow;
        this.dailyBookingLimit = dailyLimit;
        this.allowReschedule = true;
    }

    public boolean validateBooking() { return true; }
    public boolean validateCancellation() { return true; }
    public boolean validateDuration(int hours) { return hours <= maxBookingDurationHours; }
    public boolean validateAdvanceBooking(DateTime bookingDate) { return bookingDate.daysUntil(new DateTime()) <= maxAdvanceBookingDays; }
    public boolean canUserBookToday() { return true; }
    public String getPolicyDetails() { return policyName + ": " + maxAdvanceBookingDays + " days advance, " + maxBookingDurationHours + "hr max, " + dailyBookingLimit + "/day"; }

    public String getPolicyId() { return policyId; }
    public String getPolicyName() { return policyName; }
    public int getMaxAdvanceBookingDays() { return maxAdvanceBookingDays; }
    public int getMaxBookingDurationHours() { return maxBookingDurationHours; }
    public int getCancellationWindowHours() { return cancellationWindowHours; }
    public int getDailyBookingLimit() { return dailyBookingLimit; }
    public boolean isAllowReschedule() { return allowReschedule; }
}