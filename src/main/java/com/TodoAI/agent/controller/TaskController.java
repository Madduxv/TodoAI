package com.TodoAI.agent.controller;

import com.TodoAI.agent.service.*;
import com.TodoAI.agent.model.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  private TaskService ts;

  /*
   * Example useage:
   * curl -X POST http://localhost:8080/task/new \
   * -H "Content-Type: application/json" \
   * -d '{
   * "description": "Finish AI agent",
   * "priority": 1,
   * "dueDate": "2025-09-20T17:00:00"
   * }'
   */
  @PostMapping("/new")
  public ResponseEntity<Task> newTask(@RequestBody Task task) {
    task.setSource("manual");
    task.setCurrentStatus(Task.Status.PENDING);
    ts.addNewTask(task);
    return ResponseEntity.ok(task);
  }

  /*
   * Example useage:
   * curl -X GET http://localhost:8080/task/get/all
   */
  @GetMapping("/get/all")
  public ResponseEntity<List<Task>> getCurrentTasks() {
    return ResponseEntity.ok(ts.getAllTasks());
  }

  /*
   * Example useage:
   * curl -X GET http://localhost:8080/task/get/completed
   */
  @GetMapping("/get/completed")
  public ResponseEntity<List<Task>> getCompletedTasks() {
    return ResponseEntity.ok(ts.getCompletedTasks());
  }

  /*
   * Example useage:
   * curl -X GET http://localhost:8080/task/get/pending
   */
  @GetMapping("/get/pending")
  public ResponseEntity<List<Task>> getPendingTasks() {
    return ResponseEntity.ok(ts.getPendingTasks());
  }

  /*
   * Example useage:
   * curl -X DELETE http://localhost:8080/task/get/all
   */
  @DeleteMapping("delete/all")
  public ResponseEntity<String> deleteAll() {
    ts.deleteAllTasks();
    return ResponseEntity.ok("DB Cleared");
  }
}
