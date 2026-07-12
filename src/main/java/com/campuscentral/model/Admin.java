package com.campuscentral.model;

import com.campuscentral.util.DateTime;

public class Admin {
    private String adminId;
    private String userId;
    private String employeeNumber;
    private String department;
    private String positionTitle;
    private int accessLevel;
    private boolean canManageUsers;
    private boolean canManageFacilities;
    private boolean canManageKnowledgeBase;
    private boolean canManageSystemSettings;
    private DateTime lastLoginAt;
    private DateTime createdAt;
    private DateTime updatedAt;

    public Admin() {
        this.adminId = java.util.UUID.randomUUID().toString();
        this.createdAt = new DateTime();
        this.updatedAt = new DateTime();
    }

    public String getAdminId() { return adminId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getEmployeeNumber() { return employeeNumber; }
    public int getAccessLevel() { return accessLevel; }
    public void setAccessLevel(int accessLevel) { this.accessLevel = accessLevel; }
}