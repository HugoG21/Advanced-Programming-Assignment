package com.campuscentral.service;

import com.campuscentral.client.mcp.CampusMcpClient;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeBaseService {
    private final CampusMcpClient mcp;
    private boolean isLoaded;

    public KnowledgeBaseService(CampusMcpClient mcp) {
        this.mcp = mcp;
        this.isLoaded = false;
    }

    public boolean loadKnowledgeBase() { this.isLoaded = true; return true; }
    public boolean refreshKnowledgeBase() { return loadKnowledgeBase(); }

    public String getDocumentContent(String uri) {
        return mcp.readResource(uri);
    }

    public String getBookingPolicies() { return mcp.readResource("campus://handbook"); }
    public String getFacilityRules() { return mcp.readResource("campus://facilities"); }
    public String getOperatingHours() { return mcp.readResource("campus://facilities"); }
    public String getFAQContent() { return mcp.readResource("campus://faq"); }

    public List<String> searchDocuments(String query) {
        String result = mcp.callTool("search_campus_info", java.util.Map.of("query", query, "topK", 5));
        List<String> results = new ArrayList<>();
        results.add(result);
        return results;
    }

    public List<String> retrieveRelevantContent(String query) {
        return searchDocuments(query);
    }

    public boolean isLoaded() { return isLoaded; }
}