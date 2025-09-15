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
  private Status currentStatus;
  private LocalDateTime dueDate;

  public enum Status {
    PENDING,
    SCHEDULED,
    COMPLETED,
    DELETED,
  }

  // --- Constructors ---
  public Task() {

  }

  public Task(String description, String source, Integer priority, LocalDateTime dueDate) {
    this.description = description;
    this.source = source;
    this.priority = priority;
    this.dueDate = dueDate;
    this.currentStatus = Status.PENDING;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public Status getCurrentStatus() {
    return currentStatus;
  }

  public void setCurrentStatus(Status currentStatus) {
    this.currentStatus = currentStatus;
  }

  public LocalDateTime getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDateTime dueDate) {
    this.dueDate = dueDate;
  }
}
