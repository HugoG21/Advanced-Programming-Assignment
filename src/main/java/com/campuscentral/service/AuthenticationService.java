package com.campuscentral.service;

import com.campuscentral.model.User;
import com.campuscentral.util.DateTime;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthenticationService {
    private final Map<String, User> users;
    private final Map<String, String> sessions;
    private final int maxLoginAttempts;
    private final Map<String, Integer> loginAttempts;

    public AuthenticationService() {
        this.users = new HashMap<>();
        this.sessions = new HashMap<>();
        this.loginAttempts = new HashMap<>();
        this.maxLoginAttempts = 5;
    }

    public User registerUser(String fullName, String email, String studentId, String password) {
        User user = new User(fullName, email, studentId);
        user.setPasswordHash(hashPassword(password));
        user.register();
        users.put(user.getUserId(), user);
        return user;
    }

    public String authenticateUser(String email, String password) {
        for (User u : users.values()) {
            if (u.getEmail().equals(email)) {
                String hashed = hashPassword(password);
                if (u.getPasswordHash().equals(hashed) && u.authenticate()) {
                    String token = generateSessionToken();
                    sessions.put(token, u.getUserId());
                    return token;
                }
            }
        }
        return null;
    }

    public String generateVerificationCode() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public String generateSessionToken() {
        return UUID.randomUUID().toString();
    }

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            return password;
        }
    }

    public boolean logoutUser(String token) { sessions.remove(token); return true; }
    public boolean verifyEmail(String userId) {
        User u = users.get(userId);
        return u != null && u.verifyEmail();
    }
    public boolean resetPassword(String userId, String newPassword) {
        User u = users.get(userId);
        return u != null && u.changePassword(hashPassword(newPassword));
    }
    public boolean updatePassword(String userId, String oldPassword, String newPassword) {
        User u = users.get(userId);
        if (u != null && u.getPasswordHash().equals(hashPassword(oldPassword))) {
            return u.changePassword(hashPassword(newPassword));
        }
        return false;
    }
    public boolean validateSessionToken(String token) { return sessions.containsKey(token); }
    public User getUserByToken(String token) {
        String userId = sessions.get(token);
        return userId != null ? users.get(userId) : null;
    }
}