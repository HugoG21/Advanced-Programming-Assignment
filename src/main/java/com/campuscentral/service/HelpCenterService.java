package com.campuscentral.service;

import com.campuscentral.client.mcp.CampusMcpClient;
import com.campuscentral.model.FAQ;

import java.util.ArrayList;
import java.util.List;

public class HelpCenterService {
    private final KnowledgeBaseService kbService;
    private final AIAssistantService aiService;
    private final List<FAQ> faqs;

    public HelpCenterService(KnowledgeBaseService kbService, AIAssistantService aiService) {
        this.kbService = kbService;
        this.aiService = aiService;
        this.faqs = new ArrayList<>();
    }

    public List<String> searchFAQ(String query) {
        String faqContent = kbService.getFAQContent();
        List<String> results = new ArrayList<>();
        for (String line : faqContent.split("\n")) {
            if (line.toLowerCase().contains(query.toLowerCase())) {
                results.add(line);
            }
        }
        return results;
    }

    public List<String> searchKnowledgeBase(String query) {
        return kbService.searchDocuments(query);
    }

    public String processQuestion(String question) throws Exception {
        return aiService.generateResponse(question);
    }

    public String retrievePolicies() { return kbService.getBookingPolicies(); }
    public String retrieveFacilityInformation() { return kbService.getFacilityRules(); }
    public String retrieveUsageRules() { return kbService.getBookingPolicies(); }
    public String retrieveOperatingHours() { return kbService.getOperatingHours(); }
}