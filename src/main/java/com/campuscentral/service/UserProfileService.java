package com.campuscentral.service;

import com.campuscentral.model.User;
import com.campuscentral.model.UserPreference;

public class UserProfileService {
    private final AuthenticationService authService;

    public UserProfileService(AuthenticationService authService) {
        this.authService = authService;
    }

    public User getProfile(String token) { return authService.getUserByToken(token); }
    public boolean updateProfile(String token, String name, String phone) {
        User u = authService.getUserByToken(token);
        if (u != null) {
            u.setFullName(name);
            return u.updateProfile();
        }
        return false;
    }
    public boolean updateProfileImage(String token, String imageUrl) { return true; }
    public boolean updatePreferences(String token, UserPreference prefs) { return prefs.savePreferences(); }
    public boolean deactivateAccount(String token) {
        User u = authService.getUserByToken(token);
        return u != null && u.deactivateAccount();
    }
    public UserPreference getUserSettings(String userId) {
        UserPreference prefs = new UserPreference();
        prefs.setUserId(userId);
        return prefs;
    }
}