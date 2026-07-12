package com.campuscentral.model;

import com.campuscentral.common.enums.UserStatus;
import com.campuscentral.util.DateTime;

public class User {
    private String userId;
    private String fullName;
    private String email;
    private String studentId;
    private String passwordHash;
    private String phoneNumber;
    private String profileImageUrl;
    private boolean emailVerified;
    private DateTime lastLoginAt;
    private UserStatus accountStatus;
    private DateTime createdAt;
    private DateTime updatedAt;

    public User() {
        this.userId = java.util.UUID.randomUUID().toString();
        this.accountStatus = UserStatus.ACTIVE;
        this.createdAt = new DateTime();
        this.updatedAt = new DateTime();
    }

    public User(String fullName, String email, String studentId) {
        this();
        this.fullName = fullName;
        this.email = email;
        this.studentId = studentId;
    }

    public boolean register() { this.createdAt = new DateTime(); return true; }
    public boolean authenticate() { return accountStatus == UserStatus.ACTIVE; }
    public boolean logout() { return true; }
    public boolean updateProfile() { this.updatedAt = new DateTime(); return true; }
    public boolean changePassword(String newHash) { this.passwordHash = newHash; return true; }
    public boolean verifyEmail() { this.emailVerified = true; return true; }
    public boolean deactivateAccount() { this.accountStatus = UserStatus.INACTIVE; return true; }
    public boolean activateAccount() { this.accountStatus = UserStatus.ACTIVE; return true; }

    public String getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getStudentId() { return studentId; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public UserStatus getAccountStatus() { return accountStatus; }
    public DateTime getCreatedAt() { return createdAt; }
}