package com.TodoAI.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.TodoAI.agent.model.Task;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

  List<Task> findByCurrentStatus(Task.Status status);

  List<Task> findBySource(String source);
}
