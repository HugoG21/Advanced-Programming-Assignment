package com.campuscentral.model;

import com.campuscentral.util.DateTime;

public class InstitutionStaff {
    private String staffId;
    private String userId;
    private String employeeNumber;
    private String department;
    private String jobTitle;
    private String officeLocation;
    private String phoneExtension;
    private int assignedFacilityCount;
    private String employmentStatus;
    private DateTime createdAt;
    private DateTime updatedAt;

    public InstitutionStaff() {
        this.staffId = java.util.UUID.randomUUID().toString();
        this.createdAt = new DateTime();
        this.updatedAt = new DateTime();
    }

    public String getStaffId() { return staffId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getUserId() { return userId; }
    public String getEmployeeNumber() { return employeeNumber; }
    public void setEmployeeNumber(String employeeNumber) { this.employeeNumber = employeeNumber; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public String getOfficeLocation() { return officeLocation; }
}