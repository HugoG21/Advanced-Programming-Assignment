package com.campuscentral.service;

import com.campuscentral.model.User;
import java.util.HashMap;
import java.util.Map;

public class OAuthService {
    private final Map<String, User> oauthUsers;
    private final AuthenticationService authService;

    public OAuthService(AuthenticationService authService) {
        this.oauthUsers = new HashMap<>();
        this.authService = authService;
    }

    public User loginWithGoogle(String googleId, String email, String name) {
        if (oauthUsers.containsKey(googleId)) return oauthUsers.get(googleId);
        User user = new User(name, email, "GOOGLE-" + googleId);
        user.verifyEmail();
        oauthUsers.put(googleId, user);
        return user;
    }

    public User loginWithMicrosoft(String msId, String email, String name) {
        if (oauthUsers.containsKey(msId)) return oauthUsers.get(msId);
        User user = new User(name, email, "MS-" + msId);
        user.verifyEmail();
        oauthUsers.put(msId, user);
        return user;
    }

    public User createOAuthUser(String email, String name, String provider) {
        User user = new User(name, email, provider + "-" + email.hashCode());
        user.verifyEmail();
        return user;
    }

    public User getOAuthProfile(String providerId) { return oauthUsers.get(providerId); }
    public boolean validateGoogleToken(String token) { return token != null && !token.isEmpty(); }
    public boolean validateMicrosoftToken(String token) { return token != null && !token.isEmpty(); }
    public boolean linkAccount(String userId, String providerId) { return true; }
}