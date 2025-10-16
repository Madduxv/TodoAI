package com.TodoAI.agent.repository;

import com.TodoAI.agent.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {

  private Connection connection;
  private Statement statement;

  public UserRepository() throws SQLException {
    this.connection = DriverManager.getConnection("jdbc:sqlite:test.db");
    this.connection.setAutoCommit(true);
    this.statement = connection.createStatement();
  }

  public void create(Task task) throws SQLException {
    this.statement.clearBatch();
    System.out.println("adding \"" + task.getDescription() + "\" to DB");
  }

  public void read(Task task) throws SQLException {
    this.statement.clearBatch();
  }

  public void update(Task task) throws SQLException {
    this.statement.clearBatch();
  }

  public void delete(Task task) throws SQLException {
    this.statement.clearBatch();
  }

  private void execute(String sql) throws SQLException {
    statement.addBatch(sql);
    statement.executeBatch();
  }

  private String getResult() throws SQLException {
    return statement.getResultSet().toString();
  }

}
