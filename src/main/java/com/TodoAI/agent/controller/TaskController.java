package com.TodoAI.agent.controller;

import com.TodoAI.agent.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

  @Autowired
  private TaskService ts;

}
