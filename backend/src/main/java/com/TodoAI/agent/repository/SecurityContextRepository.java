
package com.TodoAI.agent.repository;

import com.TodoAI.agent.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SecurityContextRepository extends JpaRepository<User, String> {
}
