package com.capstone.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capstone.bean.RegisterBean;

/**
 * Handles database operations for user registration.
 */
public class RegistrationDAO {
  
  /**
   * Checks whether an email already exists in the specified table.
   */
  public boolean checkExistUser(String email, String table) throws SQLException, ClassNotFoundException {

    String sql = "SELECT COUNT(*) FROM " + table + " WHERE EMAIL = ?";

    try (Connection connection = DatabaseConnection.getConnection();
      PreparedStatement statement = connection.prepareStatement(sql)) {

      statement.setString(1, email.toUpperCase());

      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          return resultSet.getInt(1) > 0;
        }
      }
    }
    return false;
  }

  /**
   * Inserts a new student or faculty member into the database.
   */
  public boolean addUser(RegisterBean user, String table)
          throws SQLException, ClassNotFoundException {

      String sql;

      if ("STUDENTS".equalsIgnoreCase(table)) {
          sql = "INSERT INTO STUDENTS "
              + "(USERID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD) "
              + "VALUES (USERID_SEQ.NEXTVAL, ?, ?, ?, ?)";

      } else if ("FACULTY".equalsIgnoreCase(table)) {
          sql = "INSERT INTO FACULTY "
              + "(FACULTYID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD) "
              + "VALUES (FACULTID_SEQ.NEXTVAL, ?, ?, ?, ?)";

      } else {
          throw new SQLException("Invalid user table: " + table);
      }

      try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

          statement.setString(1, user.getFirst().toUpperCase());
          statement.setString(2, user.getLast().toUpperCase());
          statement.setString(3, user.getEmail().toUpperCase());
          statement.setString(4, user.getPassword());

          return statement.executeUpdate() > 0;
      }
  }
}
