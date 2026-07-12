package com.campuscentral.client.llm;

public interface LlmClient {
    String complete(String systemPrompt, String userPrompt) throws Exception;
    String model();
}