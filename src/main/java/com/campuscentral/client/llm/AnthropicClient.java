package com.campuscentral.client.llm;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AnthropicClient implements LlmClient {
    private final String apiKey;
    private final String model;
    private final HttpClient httpClient;

    public AnthropicClient(String apiKey, String model) {
        this.apiKey = apiKey;
        this.model = model;
        this.httpClient = HttpClient.newHttpClient();
    }

    @Override
    public String complete(String systemPrompt, String userPrompt) throws Exception {
        String jsonBody = """
            {
              "model": "%s",
              "max_tokens": 1024,
              "system": "%s",
              "messages": [
                { "role": "user", "content": "%s" }
              ]
            }
            """.formatted(model, escapeJson(systemPrompt), escapeJson(userPrompt));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.anthropic.com/v1/messages"))
                .header("content-type", "application/json")
                .header("x-api-key", apiKey)
                .header("anthropic-version", "2023-06-01")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    @Override
    public String model() { return model; }

    private String escapeJson(String s) {
        return s == null ? "" : s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");
    }
}