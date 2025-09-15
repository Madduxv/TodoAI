package com.TodoAI.agent.controller;

import com.TodoAI.agent.service.*;
import com.TodoAI.agent.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

  @Autowired
  private TaskService ts;

  @PostMapping("/new")
  public ResponseEntity<Task> newTask(@RequestBody Task task) {
    task.setSource("manual"); // set source automatically if you want
    task.setCurrentStatus(Task.Status.PENDING);
    ts.addNewTask(task);
    return ResponseEntity.ok(task);
  }
}
