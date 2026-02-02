package com.TodoAI.agent.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {

  @PostMapping("/login")
  public void login(
      @RequestBody LoginRequest request,
      HttpServletRequest httpRequest) {
    // TODO: validate user properly
    HttpSession session = httpRequest.getSession(true);
    session.setAttribute("user", request.username());
  }

  public record LoginRequest(String username, String password) {
  }
}
