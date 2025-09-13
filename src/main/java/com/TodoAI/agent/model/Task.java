package com.TodoAI.agent.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public class Task {

  @Id
  private UUID id;

  private String description;
  private String source;
  private Integer priority;

  private LocalDateTime dueDate;

  public enum status {
    PENDING,
    SCHEDULED,
    COMPLETED,
    DELETED,
  }
}
