package com.campuscentral.model;

import com.campuscentral.common.enums.BookingStatus;
import com.campuscentral.util.DateTime;
import java.util.List;

public class HelpRequest {
    private String requestId;
    private String userId;
    private String question;
    private String aiResponse;
    private double confidenceScore;
    private int feedbackRating;
    private BookingStatus status;
    private DateTime requestDate;
    private DateTime responseDate;
    private DateTime createdAt;

    public HelpRequest() {
        this.requestId = java.util.UUID.randomUUID().toString();
        this.createdAt = new DateTime();
        this.requestDate = new DateTime();
    }

    public boolean submitQuestion(String q) { this.question = q; this.requestDate = new DateTime(); return true; }
    public String retrieveAnswer() { return aiResponse; }
    public boolean closeRequest() { return true; }
    public boolean reopenRequest() { return true; }
    public boolean rateResponse(int rating) { this.feedbackRating = rating; return true; }
    public List<HelpRequest> getConversationHistory() { return List.of(this); }
    public long calculateResponseTime() { return responseDate != null ? responseDate.minutesUntil(new DateTime()) : 0; }

    public String getRequestId() { return requestId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getQuestion() { return question; }
    public void setAiResponse(String aiResponse) { this.aiResponse = aiResponse; }
    public double getConfidenceScore() { return confidenceScore; }
    public void setConfidenceScore(double confidenceScore) { this.confidenceScore = confidenceScore; }
}