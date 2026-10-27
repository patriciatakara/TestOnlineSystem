package com.capstone.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handles database operations for user authentication.
 */
public class AuthenticationDAO {

/**
 * Checks whether the provided email and password match a database record.
 *
 * @param email is the user's email.
 * @param password is the user's password.
 * @param table is the database table to search.
 * @return true if a matching record exists, otherwise false.
 * @throws SQLException if a database operation fails.
 * @throws ClassNotFoundException if the database driver cannot be found.
 */
public boolean authenticate(String email, String password, String table)
throws SQLException, ClassNotFoundException {

  // Validate the table name before including it in the SQL query.
  if (table == null || !table.matches("[A-Za-z][A-Za-z0-9_]*")) {
    throw new SQLException("Invalid table name.");
  }

  // SQL query to find a record with the matching email and password.
  String sql = "SELECT 1 FROM " + table + " WHERE EMAIL = ? AND PASSWORD = ?";

  // Open the database connection and prepare the SQL statement.
  try (Connection connection = DatabaseConnection.getConnection();
  
  PreparedStatement statement = connection.prepareStatement(sql)) {

    // Set the email parameter in uppercase.
    statement.setString(1, email.toUpperCase());

    // Set the password parameter.
    statement.setString(2, password);

    // Execute the query and retrieve the result.
    try (ResultSet resultSet = statement.executeQuery()) {

      // Return true if a matching record exists.
      if (resultSet.next()) {
        return true;
      }
    }
  }

  // Return false if no matching record was found.
    return false;
  }

}