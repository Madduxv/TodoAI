package com.TodoAI.agent.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String description;
  private String source;
  private Integer priority;

  @Enumerated(EnumType.STRING)
  private Status currentStatus;
  private LocalDateTime dueDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private User user;

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

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
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
