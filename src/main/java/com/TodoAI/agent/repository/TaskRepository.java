package com.TodoAI.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.TodoAI.agent.model.Task;
import com.TodoAI.agent.model.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

  List<Task> findByUserAndCurrentStatus(User user, Task.Status status);

  List<Task> findByUserAndSource(User user, String source);

  List<Task> findByUser(User user);

  void deleteByUser(User user);
}
