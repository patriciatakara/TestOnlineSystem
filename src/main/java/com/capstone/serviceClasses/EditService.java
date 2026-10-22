package com.capstone.serviceClasses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.capstone.bean.NewExamBean;
import com.capstone.bean.RegisterBean;
import com.capstone.model.DatabaseConnection;
/** 
 * @author Patricia Takara
 */
public class EditService {
	
	/**
	 * This method updates a user's information in the database based on their userID.
	 * @param user is a RegisterBean object which contains the updated user information (first name, last name, email, and password).
	 * @param table is the name of the database table to update the information.
	 * @param userId is the ID of the user to be updated.
	 * @return an integer value that indicates the number of rows that were updated in the database. The value will be greater than 0 if the update was successful, 
	 * and 0 if it was not.
	 * @throws SQLException for errors occurring while accessing the database.
	 */
	public int updateUser(RegisterBean user, String table, String userId) throws SQLException {

	    String newFirst = user.getFirst();
	    String newLast = user.getLast();
	    String newEmail = user.getEmail();
	    String newPassword = user.getPassword();
	    String userID = userId;
	    String tableName = table;
	    String id;
	    int rowsUpdated = 0;
	    
	        
	    if(tableName.equals("FACULTY")) {
	        id = "FACULTYID";
	    } else {
	        id = "USERID"; 
	    } 
	    
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {	
			connection = DatabaseConnection.getConnection();
			String sql1 = "select * from " + tableName + " where " + id + " = " + userID;
			statement = connection.prepareStatement(sql1);
			resultSet = statement.executeQuery();
			resultSet.next();
			
			String oldFirst = resultSet.getString("FIRSTNAME");
		    String oldLast = resultSet.getString("LASTNAME");
		    String oldEmail = resultSet.getString("EMAIL");
		    String oldPassword = resultSet.getString("PASSWORD");
		    
	        // Updates the retrieved data using the values submitted by the form.
            if (newFirst.isEmpty()) {
            	newFirst = oldFirst;
            }
            if (newLast.isEmpty()) {
            	newLast = oldLast;
            }
            if (newEmail.isEmpty()) {
            	newEmail = oldEmail;
            }
            if (newPassword.isEmpty()) {
            	newPassword = oldPassword;
            }

        } catch (Exception e) {
        	e.printStackTrace();
	    
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (connection != null) {
                connection.close();
            }
        }	 
           
        
	    try {
	        connection = DatabaseConnection.getConnection();
	        String sql2 = "update " + tableName + " set FIRSTNAME = ?, LASTNAME = ?, EMAIL =?, PASSWORD = ? where " + id + " = " + userID;
	        statement = connection.prepareStatement(sql2);
	        statement.setString(1, newFirst.toUpperCase());
	        statement.setString(2, newLast.toUpperCase());
	        statement.setString(3, newEmail.toUpperCase());
	        statement.setString(4, newPassword.toUpperCase());
	        rowsUpdated = statement.executeUpdate();      
	        
	    } catch (Exception e) {
	    	e.printStackTrace();
	    
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	    return rowsUpdated;
	}

	
	
	/**
	 * This method updates a question of a specified table(exam) in the database based on the question ID (qid).
	 * The method first retrieves the current values for the question and then compares them to the new values provided in the question object.
	 * If a field in the question object is empty, the method retains the original value for that field.
	 * @param table is the name of the table/exam.
	 * @param qid is the question ID.
	 * @param question is a NewExamBean object which contains the updated question information (question text, multiple choices, and correct answer).
	 * @return the number of rows updated in the table as an integer. The value will be greater than 0 if the update was successful, 
	 * and 0 if it was not.
	 * @throws SQLException for errors occurring while accessing the database.
	 */
	public int updateQuestion(String table, String qid, NewExamBean question) throws SQLException {
		int rowsUpdated = 0;
		
		String questionText = question.getQtext();
	    String op1 = question.getOpt1();
	    String op2 = question.getOpt2();
	    String op3 = question.getOpt3();
	    String op4 = question.getOpt4();
	    String correctAnswer = question.getCorrectAnswer();
	    String tableName = table;
		
	    Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;	
		
		// Current values for the question are retrieved.
		try {	
			connection = DatabaseConnection.getConnection();
			String sql = "select * from " + tableName + " where qid = ? ";
			statement = connection.prepareStatement(sql);
			statement.setString(1, qid);
			resultSet = statement.executeQuery();
			resultSet.next();
			
	        String oldQuestionText = resultSet.getString("QUESTION");
	        String oldOp1 = resultSet.getString("OP1");
	        String oldOp2 = resultSet.getString("OP2");
	        String oldOp3 = resultSet.getString("OP3");
	        String oldOp4 = resultSet.getString("OP4");
	        String oldCorrectAnswer = resultSet.getString("CORRECTANS");
	        
	        // Updates the retrieved data using the values submitted by the form.
            if (questionText.isEmpty()) {
                questionText = oldQuestionText;
            }
            if (op1.isEmpty()) {
                op1 = oldOp1;
            }
            if (op2.isEmpty()) {
                op2 = oldOp2;
            }
            if (op3.isEmpty()) {
                op3 = oldOp3;
            }
            if (op4.isEmpty()) {
                op4 = oldOp4;
            }
            if (correctAnswer.isEmpty()) {
                correctAnswer = oldCorrectAnswer;
            }
            
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (connection != null) {
                connection.close();
            }
        }	

		
		try {	
			connection = DatabaseConnection.getConnection();    
            String sql2 = "update " +  tableName + " set QUESTION = ?, OP1= ?, OP2 =?, OP3 = ?, OP4 = ?, CORRECTANS = ? where QID =? ";
            statement = connection.prepareStatement(sql2);
            statement.setString(1, questionText);
			statement.setString(2, op1);
			statement.setString(3, op2);
			statement.setString(4, op3);
		    statement.setString(5, op4);
			statement.setString(6, correctAnswer);
			statement.setString(7, qid);
			rowsUpdated = statement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
			e.printStackTrace();
		
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (connection != null) {
                connection.close();
            }
        }	
		return rowsUpdated;
}

	
	/**
	 * This method updates a student's grade from a specified exam. The student ID is used to make this change.   
	 * @param table is the table/exam's grade list containing the grades from all students that took the exam.
	 * @param userId is the student ID used to identify the student having his grade changed.
	 * @param first is the student first name.
	 * @param last is the student last name.
	 * @param grade is the student's grade.
	 * @param letterGrade is the student's letter grade (A, B, C, D, E, F).
	 * @param submission is the exam's submission date.
	 * @return the number of rows updated in the table as an integer. The value will be greater than 0 if the update was successful, 
	 * and 0 if it was not.
	 * @throws SQLException SQLException for errors occurring while accessing the database.
	 */
	public int updateGrade(String table, int userId, String first, String last, int grade, String letterGrade, String submission) throws SQLException {
	    
		int newUserId = userId;
		String newFirst = first;
		String newLast = last;
	    int newGrade = grade;
	    String newLetterGrade = letterGrade;
	    String tableName = table;
	    String newSubmissionDate = submission;
	    
		int rowsUpdated = 0;
		Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    
	    try {	
	    	connection = DatabaseConnection.getConnection();
			String sql1 = "select USERID, FIRSTNAME, LASTNAME, GRADE, LETTER, SUBMISSION from STUDENTS natural join " + tableName + " where USERID = ? ";
			statement = connection.prepareStatement(sql1);
			statement.setInt(1, userId);
			resultSet = statement.executeQuery();
			resultSet.next();
			
	        int oldUserID = resultSet.getInt("USERID");
	        String oldFirst= resultSet.getString("FIRSTNAME");
	        String oldLast = resultSet.getString("LASTNAME");
			String oldLetterGrade = resultSet.getString("LETTER");
			String oldSubmissionDate = resultSet.getString("SUBMISSION");
			
			if (newUserId == 0) {
				newUserId = oldUserID;
			}
	        if (newFirst.isEmpty()) {
	        	newFirst = oldFirst;
	        }
	        if (newLast.isEmpty()) {
	        	newLast = oldLast;
	        }
	        if (newGrade == 0) {
	              newGrade = 0;
	        }
	        if (newLetterGrade.isEmpty() || newLetterGrade == null) {
	        	newLetterGrade = oldLetterGrade;
	        }
	        if (newSubmissionDate.trim().isEmpty() || newSubmissionDate == null) {
	        	newSubmissionDate = oldSubmissionDate;
	        }
	        
	        
	    } catch (Exception e) {
	    	 e.printStackTrace();
	    
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}   
	    
	    try {	 
	    	connection = DatabaseConnection.getConnection();
	        String sql2 = "update " + tableName + " set GRADE = ?, LETTER =?, SUBMISSION = TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS') where USERID = ?";
	        statement = connection.prepareStatement(sql2);
	        statement.setInt(1, newGrade);
	        statement.setString(2, newLetterGrade);
	        statement.setString(3, newSubmissionDate);
	        statement.setInt(4, newUserId);
	        rowsUpdated = statement.executeUpdate();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        
	    } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
       }	
	    return rowsUpdated;
	}
	
}