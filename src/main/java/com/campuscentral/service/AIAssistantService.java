package com.campuscentral.service;

import com.campuscentral.client.llm.LlmClient;
import com.campuscentral.client.mcp.CampusMcpClient;
import com.campuscentral.model.HelpRequest;

import java.util.Map;

public class AIAssistantService {
    private final RagService ragService;
    private final KnowledgeBaseService kbService;
    private final int helpRequestHistoryLimit;
    private final double confidenceThreshold;

    public AIAssistantService(RagService ragService, KnowledgeBaseService kbService) {
        this.ragService = ragService;
        this.kbService = kbService;
        this.helpRequestHistoryLimit = 10;
        this.confidenceThreshold = 0.5;
    }

    public HelpRequest processQuestion(String userId, String question) throws Exception {
        HelpRequest request = new HelpRequest();
        request.setUserId(userId);
        request.submitQuestion(question);

        String answer = generateResponse(question);
        request.setAiResponse(answer);

        double confidence = calculateConfidenceScore(question, answer);
        request.setConfidenceScore(confidence);

        return request;
    }

    public String generateResponse(String question) throws Exception {
        RagService.RagResult result = ragService.ask(question, "general campus services");
        return result.answer();
    }

    public String answerFAQ(String question) {
        String faqContent = kbService.getFAQContent();
        if (faqContent.toLowerCase().contains(question.toLowerCase())) {
            return faqContent;
        }
        return "I couldn't find a direct FAQ match for your question.";
    }

    public String retrieveFacilityInfo() { return kbService.getFacilityRules(); }
    public String retrieveBookingInfo() { return kbService.getBookingPolicies(); }
    public java.util.List<String> retrieveKnowledge(String query) { return kbService.retrieveRelevantContent(query); }

    public double calculateConfidenceScore(String question, String answer) {
        if (answer == null || answer.isEmpty()) return 0.0;
        if (answer.toLowerCase().contains("not sure") || answer.toLowerCase().contains("don't know")) return 0.3;
        return 0.85;
    }
}