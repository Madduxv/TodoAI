package com.TodoAI.agent.controller;

import com.TodoAI.agent.service.*;
import com.TodoAI.agent.model.*;
import com.TodoAI.agent.repository.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @Autowired
  private UserRepository userRepository;

  /*
   * Example useage:
   * curl -b cookies.txt -X POST
   * http://localhost:8080/task/new \
   * -H "Content-Type: application/json" \
   * -d '{
   * "description": "Finish AI agent",
   * "priority": 1,
   * "dueDate": "2025-09-20T17:00:00"
   * }'
   */
  @PostMapping("/new")
  public ResponseEntity<String> newTask(@RequestBody Task task, @AuthenticationPrincipal UserDetails userDetails) {
    User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
    task.setUser(user);
    task.setSource("manual");
    task.setCurrentStatus(Task.Status.PENDING);
    taskService.addNewTask(task);
    return ResponseEntity.ok("Added task:\n" + task.getDescription() + "\nfor user: " + user.getUsername() + "\n");
  }

  /*
   * Example useage:
   * curl -b cookies.txt -X GET
   * http://localhost:8080/task/get/all | jq
   */
  @GetMapping("/get/all")
  public ResponseEntity<List<Task>> getCurrentTasks(@AuthenticationPrincipal UserDetails userDetails) {
    User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
    return ResponseEntity.ok(taskService.getAllTasks(user));
  }

  /*
   * Example useage:
   * curl -b cookies.txt -X GET
   * http://localhost:8080/task/get/completed | jq
   */
  @GetMapping("/get/completed")
  public ResponseEntity<List<Task>> getCompletedTasks(@AuthenticationPrincipal UserDetails userDetails) {
    User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
    return ResponseEntity.ok(taskService.getCompletedTasks(user));
  }

  /*
   * Example useage:
   * curl -b cookies.txt -X GET
   * http://localhost:8080/task/get/pending | jq
   */
  @GetMapping("/get/pending")
  public ResponseEntity<List<Task>> getPendingTasks(@AuthenticationPrincipal UserDetails userDetails) {
    User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
    return ResponseEntity.ok(taskService.getPendingTasks(user));
  }

  /*
   * Example useage:
   * curl -b cookies.txt -X DELETE
   * http://localhost:8080/task/delete/all
   */
  @DeleteMapping("/delete/all")
  public ResponseEntity<String> deleteAll(@AuthenticationPrincipal UserDetails userDetails) {
    User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
    taskService.deleteAllUserTasks(user);
    return ResponseEntity.ok("Tasks Cleared");
  }
}
