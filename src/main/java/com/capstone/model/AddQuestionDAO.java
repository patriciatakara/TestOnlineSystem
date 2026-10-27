package com.capstone.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.capstone.bean.NewExamBean;

/**
 * Handles database operations for adding exam questions.
 */
public class AddQuestionDAO {
  
  /**
   * Inserts a new question into the specified exam table.
   *
   * @param newQ contains the question text, options, and correct answer.
   * @param tableName is the name of the exam table.
   * @throws SQLException if a database operation fails.
   * @throws ClassNotFoundException if the database driver cannot be found.
   */
  public void addQuestion(NewExamBean newQ, String tableName) throws SQLException, ClassNotFoundException {

    // Validate the table name because SQL table names cannot use a ? placeholder.
    if (tableName == null || tableName.isEmpty() || tableName.startsWith(" ")) {
      throw new SQLException("Invalid exam table name.");
    }

    // SQL query to insert a question into the exam table.
    String sql = "INSERT INTO " + tableName
      + " (question, op1, op2, op3, op4, correctans)"
      + " VALUES (?, ?, ?, ?, ?, ?)";

    // Open the database connection and prepare the SQL statement.
    try (Connection connection = DatabaseConnection.getConnection();
      
    PreparedStatement statement = connection.prepareStatement(sql)) {

      // Set the question text.
      statement.setString(1, newQ.getQtext());

      // Set the first answer option.
      statement.setString(2, newQ.getOpt1());

      // Set the second answer option.
      statement.setString(3, newQ.getOpt2());

      // Set the third answer option.
      statement.setString(4, newQ.getOpt3());

      // Set the fourth answer option.
      statement.setString(5, newQ.getOpt4());

      // Set the correct answer.
      statement.setString(6, newQ.getCorrectAnswer());

      // Execute the INSERT query.
      statement.executeUpdate();
    }
  }
  
}