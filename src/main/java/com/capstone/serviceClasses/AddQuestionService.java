package com.capstone.serviceClasses;
import com.capstone.bean.NewExamBean;
import com.capstone.model.AddQuestionDAO;

/**
 * Handles business logic for adding exam questions.
 */
public class AddQuestionService {
	
  // Create an object to access the database operations.
  private static final AddQuestionDAO addQuestionDAO = new AddQuestionDAO();

	/**
	 * This method adds questions to a specific exam. 
	 * A NewExamBean object is also passed as a parameter. it contains the question text, multiple choices, and the *	  correct answer.
	 * @param newQ is the parameter for a NewExamBean object containing the contents of a question.
	 * @param tableName is the parameter for the exam's name.
	 * @throws Exception for any errors occurring during its execution.
	 */
	public static void addQuestion(NewExamBean newQ, String tableName) throws Exception {
		
		// Check whether question information was provided.
    if (newQ == null) {	
			throw new IllegalArgumentException("Question information cannot be null.");
		}

		//Validate whether the table exists.
		TableValidationService.validateExamTables(tableName);

	  // Add the validated question to the specified exam table.
		addQuestionDAO.addQuestion(newQ, tableName);
    }

}