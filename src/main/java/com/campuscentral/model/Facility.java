package com.campuscentral.model;

import com.campuscentral.common.enums.FacilityType;
import com.campuscentral.common.enums.MaintenanceStatus;
import com.campuscentral.util.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Facility {
    private String facilityId;
    private String facilityCode;
    private String facilityName;
    private FacilityType facilityType;
    private String description;
    private String location;
    private int capacity;
    private String openingTime;
    private String closingTime;
    private MaintenanceStatus status;
    private String imageUrl;
    private String rulesReference;
    private DateTime createdAt;
    private DateTime updatedAt;
    private List<AvailabilitySlot> slots;

    public Facility() {
        this.facilityId = java.util.UUID.randomUUID().toString();
        this.status = MaintenanceStatus.OPERATIONAL;
        this.slots = new ArrayList<>();
        this.createdAt = new DateTime();
        this.updatedAt = new DateTime();
    }

    public Facility(String facilityCode, String facilityName, FacilityType facilityType, String location, int capacity) {
        this();
        this.facilityCode = facilityCode;
        this.facilityName = facilityName;
        this.facilityType = facilityType;
        this.location = location;
        this.capacity = capacity;
    }

    public String getDetails() { return facilityName + " (" + facilityCode + ") - " + facilityType + " - Cap: " + capacity; }
    public boolean checkAvailability() { return status == MaintenanceStatus.OPERATIONAL; }
    public List<AvailabilitySlot> getAvailableSlots() { return slots.stream().filter(AvailabilitySlot::isAvailable).toList(); }
    public String getOperatingHours() { return openingTime + " - " + closingTime; }
    public String getFacilityRules() { return rulesReference; }
    public boolean updateStatus(MaintenanceStatus newStatus) { this.status = newStatus; this.updatedAt = new DateTime(); return true; }
    public double calculateOccupancyRate() { return slots.isEmpty() ? 0 : (double) slots.stream().filter(s -> !s.isAvailable()).count() / slots.size() * 100; }

    public String getFacilityId() { return facilityId; }
    public String getFacilityCode() { return facilityCode; }
    public void setFacilityCode(String facilityCode) { this.facilityCode = facilityCode; }
    public String getFacilityName() { return facilityName; }
    public FacilityType getFacilityType() { return facilityType; }
    public String getLocation() { return location; }
    public int getCapacity() { return capacity; }
    public void setOpeningTime(String openingTime) { this.openingTime = openingTime; }
    public void setClosingTime(String closingTime) { this.closingTime = closingTime; }
    public MaintenanceStatus getStatus() { return status; }
    public void setRulesReference(String rulesReference) { this.rulesReference = rulesReference; }
}