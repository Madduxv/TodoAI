package com.TodoAI.agent.service;

import com.TodoAI.agent.model.Task;
import com.TodoAI.agent.repository.TaskRepository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;

  public void addNewTask(Task task) {
    try {
      addToCalendar(task, sendPrompt(task.getDescription()));
    } catch (SQLException e) {
      System.out.println("Unable to add event to calendar: ");
      e.printStackTrace();
    }
  }

  private String sendPrompt(String prompt) {
    return ("Response to " + prompt);
  }

  private void addToCalendar(Task taskToAdd, String response) throws SQLException {
    System.out.println("Adding \"" + response + "\" to calendar for the user " + taskToAdd.getId());
    taskRepository.save(taskToAdd);
    // item to db -> db to user's calendar when requested
  }

  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  public List<Task> getPendingTasks() {
    return taskRepository.findByCurrentStatus(Task.Status.PENDING);
  }

  public List<Task> getCompletedTasks() {
    return taskRepository.findByCurrentStatus(Task.Status.COMPLETED);
  }

  public void deleteAllTasks() {
    taskRepository.deleteAll();
  }

}
