package com.campuscentral.client.mcp;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import io.modelcontextprotocol.spec.McpSchema;
import io.modelcontextprotocol.spec.McpSchema.CallToolRequest;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import io.modelcontextprotocol.spec.McpSchema.ClientCapabilities;
import io.modelcontextprotocol.spec.McpSchema.GetPromptRequest;
import io.modelcontextprotocol.spec.McpSchema.GetPromptResult;
import io.modelcontextprotocol.spec.McpSchema.ReadResourceRequest;
import io.modelcontextprotocol.spec.McpSchema.ReadResourceResult;
import io.modelcontextprotocol.spec.McpSchema.TextContent;
import io.modelcontextprotocol.spec.McpSchema.TextResourceContents;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CampusMcpClient implements AutoCloseable {
    private final String baseUrl;
    private McpSyncClient client;

    public CampusMcpClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public McpSchema.InitializeResult connect() {
        HttpClientSseClientTransport transport = HttpClientSseClientTransport.builder(baseUrl)
                .sseEndpoint("/sse")
                .build();

        this.client = McpClient.sync(transport)
                .requestTimeout(Duration.ofSeconds(30))
                .capabilities(ClientCapabilities.builder().build())
                .build();

        McpSchema.InitializeResult init = client.initialize();
        return init;
    }

    public List<McpSchema.Tool> listTools() { return client.listTools().tools(); }
    public List<McpSchema.Resource> listResources() { return client.listResources().resources(); }
    public List<McpSchema.Prompt> listPrompts() { return client.listPrompts().prompts(); }

    public String callTool(String name, Map<String, Object> arguments) {
        CallToolResult result = client.callTool(new CallToolRequest(name, arguments));
        String text = result.content().stream()
                .filter(c -> c instanceof TextContent)
                .map(c -> ((TextContent) c).text())
                .collect(Collectors.joining("\n"));
        return Boolean.TRUE.equals(result.isError()) ? "ERROR: " + text : text;
    }

    public String readResource(String uri) {
        ReadResourceResult result = client.readResource(new ReadResourceRequest(uri));
        return result.contents().stream()
                .filter(c -> c instanceof TextResourceContents)
                .map(c -> ((TextResourceContents) c).text())
                .collect(Collectors.joining("\n"));
    }

    public String getPrompt(String name, Map<String, Object> arguments) {
        GetPromptResult result = client.getPrompt(new GetPromptRequest(name, arguments));
        return result.messages().stream()
                .map(m -> m.content() instanceof TextContent tc ? tc.text() : "")
                .collect(Collectors.joining("\n"));
    }

    @Override
    public void close() {
        if (client != null) {
            client.closeGracefully();
            client = null;
        }
    }
}