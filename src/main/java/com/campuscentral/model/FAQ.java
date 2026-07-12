package com.campuscentral.model;

import com.campuscentral.common.enums.NotificationType;
import com.campuscentral.util.DateTime;

import java.util.List;

public class FAQ {
    private String faqId;
    private String question;
    private String answer;
    private NotificationType category;
    private List<String> keywords;
    private int displayOrder;
    private boolean isActive;
    private DateTime createdAt;
    private DateTime updatedAt;

    public FAQ() {
        this.faqId = java.util.UUID.randomUUID().toString();
        this.isActive = true;
        this.createdAt = new DateTime();
        this.updatedAt = new DateTime();
    }

    public FAQ(String question, String answer, NotificationType category) {
        this();
        this.question = question;
        this.answer = answer;
        this.category = category;
    }

    public boolean createFAQ() { return true; }
    public boolean updateFAQ() { this.updatedAt = new DateTime(); return true; }
    public boolean activateFAQ() { this.isActive = true; return true; }
    public boolean deactivateFAQ() { this.isActive = false; return true; }
    public boolean searchFAQ(String query) { return question != null && question.toLowerCase().contains(query.toLowerCase()); }
    public String getAnswer() { return answer; }

    public String getFaqId() { return faqId; }
    public String getQuestion() { return question; }
    public NotificationType getCategory() { return category; }
    public List<String> getKeywords() { return keywords; }
    public int getDisplayOrder() { return displayOrder; }
    public boolean isActive() { return isActive; }
}