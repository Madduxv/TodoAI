package com.TodoAI.agent.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AgentService {

  public record ParsedTask(String summary, String dueDate) {
  }

  public String sendPrompt(String userInput) {
    try {
      String prompt = """
          You are a task parsing assistant.

          Extract the task into JSON with this schema:
          {
          "summary": string,
          "dueDate": string | null
          }

          Rules:
          - Respond with JSON ONLY
          - No markdown
          - No explanation

          Task:
          %s
          """.formatted(userInput);

      HttpClient client = HttpClient.newHttpClient();

      String body = """
          {
          "model": "mistral",
          "prompt": %s,
          "stream": false
          }
          """.formatted(jsonEscape(prompt));

      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create("http://localhost:11434/api/generate"))
          .header("Content-Type", "application/json")
          .POST(HttpRequest.BodyPublishers.ofString(body))
          .build();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      return extractResponse(response.body());

    } catch (Exception e) {
      throw new RuntimeException("Ollama request failed", e);
    }
  }

  private static String jsonEscape(String s) {
    return "\"" + s.replace("\\", "\\\\")
        .replace("\"", "\\\"")
        .replace("\n", "\\n") + "\"";
  }

  private String extractResponse(String body) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode node = mapper.readTree(body);
    return node.get("response").asText();
  }

}
