package com.campuscentral.model;

import com.campuscentral.util.DateTime;

public class AvailabilitySlot {
    private String slotId;
    private String facilityId;
    private DateTime slotDate;
    private String startTime;
    private String endTime;
    private int maximumCapacity;
    private int remainingCapacity;
    private boolean isAvailable;
    private DateTime createdAt;
    private DateTime updatedAt;

    public AvailabilitySlot() {
        this.slotId = java.util.UUID.randomUUID().toString();
        this.isAvailable = true;
        this.createdAt = new DateTime();
        this.updatedAt = new DateTime();
    }

    public AvailabilitySlot(String facilityId, DateTime slotDate, String startTime, String endTime, int maxCapacity) {
        this();
        this.facilityId = facilityId;
        this.slotDate = slotDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maximumCapacity = maxCapacity;
        this.remainingCapacity = maxCapacity;
    }

    public boolean markAvailable() { this.isAvailable = true; return true; }
    public boolean markUnavailable() { this.isAvailable = false; return true; }
    public boolean validateSlot() { return startTime != null && endTime != null && maximumCapacity > 0; }
    public boolean reserveSlot() { if (remainingCapacity > 0) { remainingCapacity--; return true; } return false; }
    public boolean releaseSlot() { if (remainingCapacity < maximumCapacity) { remainingCapacity++; return true; } return false; }
    public int getDurationInMinutes() { return 60; }
    public boolean isExpired() { return slotDate != null && slotDate.isBefore(new DateTime()); }
    public boolean isAvailable() { return isAvailable && remainingCapacity > 0; }

    public String getSlotId() { return slotId; }
    public String getFacilityId() { return facilityId; }
    public DateTime getSlotDate() { return slotDate; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public int getRemainingCapacity() { return remainingCapacity; }
}