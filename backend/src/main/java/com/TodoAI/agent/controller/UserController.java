package com.TodoAI.agent.controller;

// import com.TodoAI.agent.service.UserService;
import com.TodoAI.agent.repository.UserRepository;
import com.TodoAI.agent.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  // @Autowired
  // private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder encoder;

  /*
   * Example usage:
   * curl -X POST -H "Content-type: application/json" \
   * -d '{"username": "testUser", "password": "testPassword"}' \
   * http://localhost:8080/user/register
   */
  @PostMapping("/register")
  public String register(@RequestBody User user) {
    user.setPassword(encoder.encode(user.getPassword()));
    userRepository.save(user);
    return "User registered successfully!\r\n";
  }

  /*
   * Login Example:
   * curl -c cookies.txt -d "username=testUser&password=testPassword"
   * http://localhost:8080/login -v
   */
}
