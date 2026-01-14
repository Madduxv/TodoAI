package com.TodoAI.agent.service;

import com.TodoAI.agent.model.Task;
import com.TodoAI.agent.model.User;
import com.TodoAI.agent.repository.TaskRepository;
import com.TodoAI.agent.service.AgentService.ParsedTask;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;

  public void addNewTask(Task task) {
    try {
      String json = sendPrompt(task.getDescription());

      ParsedTask parsed = new ObjectMapper().readValue(json, ParsedTask.class);

      task.setDescription(parsed.summary());

      if (parsed.dueDate() != null) {
        task.setDueDate(LocalDateTime.parse(parsed.dueDate()));
      }
      System.out.println(task.getDescription() + " : " + task.getDueDate());

      taskRepository.save(task);
      addToCalendar(task, json);

    } catch (Exception e) {
      System.out.println("Unable to add event:");
      e.printStackTrace();
    }
  }

  // try {
  // addToCalendar(task, sendPrompt(task.getDescription()));
  // } catch (SQLException e) {
  // System.out.println("Unable to add event to calendar: ");
  // e.printStackTrace();
  // }
  // }

  private String sendPrompt(String prompt) {
    return ("Response to " + prompt);
  }

  private void addToCalendar(Task taskToAdd, String response) throws SQLException {
    System.out
        .println("Adding \"" + response + "\" to calendar for the user " + taskToAdd.getUser().getUsername() + "\n");
    taskRepository.save(taskToAdd);
    // item to db -> db to user's calendar when requested
  }

  public List<Task> getAllTasks(User user) {
    return taskRepository.findByUser(user);
  }

  public List<Task> getPendingTasks(User user) {
    return taskRepository.findByUserAndCurrentStatus(user, Task.Status.PENDING);
  }

  public List<Task> getCompletedTasks(User user) {
    return taskRepository.findByUserAndCurrentStatus(user, Task.Status.COMPLETED);
  }

  public void deleteAllTasks() {
    taskRepository.deleteAll();
  }

  public void deleteAllUserTasks(User user) {
    taskRepository.deleteByUser(user);
  }

}
