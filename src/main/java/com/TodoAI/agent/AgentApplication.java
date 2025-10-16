package com.TodoAI.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgentApplication {

  public static void main(String[] args) {
    try {
      SpringApplication.run(AgentApplication.class, args);
    } catch (Exception e) {
      System.out.println("Server Startup Failed: Failed to connect to DB");
      e.printStackTrace();
    }
  }

}
