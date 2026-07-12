package com.campuscentral.model;

import com.campuscentral.common.enums.UserRole;
import com.campuscentral.util.DateTime;

public class UserPreference {
    private String preferenceId;
    private String userId;
    private boolean notificationsEnabled;
    private boolean emailRemindersEnabled;
    private boolean smsRemindersEnabled;
    private UserRole theme;
    private String language;
    private DateTime createdAt;
    private DateTime updatedAt;

    public UserPreference() {
        this.preferenceId = java.util.UUID.randomUUID().toString();
        this.notificationsEnabled = true;
        this.emailRemindersEnabled = true;
        this.language = "en";
        this.createdAt = new DateTime();
        this.updatedAt = new DateTime();
    }

    public boolean updateTheme(UserRole theme) { this.theme = theme; return true; }
    public boolean updateLanguage(String lang) { this.language = lang; return true; }
    public boolean enableNotifications() { this.notificationsEnabled = true; return true; }
    public boolean disableNotifications() { this.notificationsEnabled = false; return true; }
    public boolean enableEmailReminders() { this.emailRemindersEnabled = true; return true; }
    public boolean disableEmailReminders() { this.emailRemindersEnabled = false; return true; }
    public boolean enableSMSReminders() { this.smsRemindersEnabled = true; return true; }
    public boolean disableSMSReminders() { this.smsRemindersEnabled = false; return true; }
    public boolean savePreferences() { this.updatedAt = new DateTime(); return true; }
    public boolean resetPreferences() { this.notificationsEnabled = true; this.emailRemindersEnabled = true; this.smsRemindersEnabled = false; return true; }

    public String getPreferenceId() { return preferenceId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public boolean isNotificationsEnabled() { return notificationsEnabled; }
    public boolean isEmailRemindersEnabled() { return emailRemindersEnabled; }
    public boolean isSmsRemindersEnabled() { return smsRemindersEnabled; }
    public String getLanguage() { return language; }
}