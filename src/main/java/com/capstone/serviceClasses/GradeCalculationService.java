package com.capstone.serviceClasses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.capstone.model.DatabaseConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;
/**
 * @author Patricia Takara
 */
public class GradeCalculationService {

	/**
	 * This method stores a student's score and the exam's submission date on the database.
	 * @param userId is the student's ID.
	 * @param score is the student's grade on an exam.
	 * @param exam is the name of the exam.
	 * @param letterGrade is the student's letter grade (A, B, C, D, E, F).
	 * @param date is the exam's submission date.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 * @throws SQLException for errors occurring while accessing the database.
	 */
	public void saveScore(int userId, int score, String exam, String letterGrade, String date) throws ClassNotFoundException, SQLException {
		
		Connection connection = null;
		PreparedStatement statement = null;
		String examGrade = exam+"_grade";

		try {
			connection = DatabaseConnection.getConnection();

			 String query = "INSERT INTO " + examGrade
            + " (USERID, GRADE, EXAM, LETTER, SUBMISSION)"
            + " VALUES (?, ?, ?, ?, "
            + "TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS'))";
			
			statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			statement.setInt(2, score);
			statement.setString(3,exam);
			statement.setString(4,letterGrade);
			statement.setString(5,date);
			statement.executeUpdate(); 
		
		} catch (SQLException e) {
		    e.printStackTrace();
		    
		} finally {
		    if (statement != null) {
		    	statement.close();
		    }
		    if (connection != null) {
		        connection.close();
		    }
		}
	}
	
	
	/**
	 * This method calculates the grade in a percentage format.
	 * @param questionCount is the number of questions of an exam.
	 * @param score is the grade from the exam.
	 * @return a double containing the grade in a percentage format.
	 */
	public double percentage(int questionCount, int score) {
		
		double percentage;
		percentage = ((double)score/questionCount)*100 ;
		DecimalFormat df = new DecimalFormat("#.##");
	    String formattedPercentage = df.format(percentage);
	    percentage = Double.parseDouble(formattedPercentage);
	    return percentage;
		
	}
	
	
	/**
	 * This method assign letter grades to the student's grade.
	 * @param grade is the student's grade in numeric form. 
	 * @return a letter grade representing the grade value.
	 */
	public String getLetterGrade(double grade) {
		
	    String letterGrade = "";
	    
	    if (grade >= 89.0) {
	        letterGrade = "A";
	    } else if (grade >= 79.0) {
	        letterGrade = "B";
	    } else if (grade >= 69.0) {
	        letterGrade = "C";
	    } else if (grade >= 59.0) {
	        letterGrade = "D";
	    } else {
	        letterGrade = "F";
	    }
	    return letterGrade;
	}

	
	/**
	 * This method returns a timestamp representing the current data and time.
	 * It will be generated when the student submits the exam. 
	 * @return a formatted timestamp string displaying the current time and date.
	 */
	public String timeStamp() {
		
	    Date now = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			formatter.setTimeZone(java.util.TimeZone.getTimeZone("America/New_York"));

	    return formatter.format(now);
	}
	
}