package com.capstone.bean;

import java.io.Serializable;

/**
 * @author Patricia Takara
 */
public class NewExamBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected String examName;
	protected String qid;
	protected String qtext;
	protected String opt1, opt2, opt3, opt4;
	protected String correctAnswer;
	
	/**
	 * Default constructor
	 */
	public NewExamBean() {
	}
	
	/**
	 * This constructor builds a new exam object with a specified name.
	 * @param examName is the exam name
	 */
	public NewExamBean(String examName) {
		this.examName = examName;
	}
	
	/**
	 * This constructor builds a new exam object with a specified name, question text, multiple choices, and a correct answer.
	 * @param examName is the exam name.
	 * @param questionText is the question.
	 * @param ansOption1 is the first of the multiple choices answers.
	 * @param ansOption2 is the second of the multiple choices answers.
	 * @param ansOption3 is the third of the multiple choices answers.
	 * @param ansOption4 is the fourth of the multiple choices answers.
	 * @param correctAnswer is the correct answer.
	 */
	public NewExamBean( String examName, String questionText, String ansOption1, String ansOption2, String ansOption3, String ansOption4,
			String correctAnswer) {	
		this.examName = examName;
		this.qtext = questionText;
		this.opt1 = ansOption1;
		this.opt2 = ansOption2;
		this.opt3 = ansOption3;
		this.opt4 = ansOption4;
		this.correctAnswer = correctAnswer;
	}

	/**
	 * This constructor builds a new exam object with a specified question text, multiple choices, and a correct answer.
	 * @param questionText is the question.
	 * @param ansOption1 is the first of the multiple choices answers.
	 * @param ansOption2 is the second of the multiple choices answers.
	 * @param ansOption3 is the third of the multiple choices answers.
	 * @param ansOption4 is the fourth of the multiple choices answers.
	 * @param correctAnswer is the correct answer.
	 */
	public NewExamBean(String questionText, String ansOption1, String ansOption2, String ansOption3, String ansOption4,
			String correctAnswer) {	
		this.qtext = questionText;
		this.opt1 = ansOption1;
		this.opt2 = ansOption2;
		this.opt3 = ansOption3;
		this.opt4 = ansOption4;
		this.correctAnswer = correctAnswer;
	}
	
	
	public void setTableName(String examName) {
		this.examName = examName;
	}
	public String getTableName() {
		return examName;
	}

	
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getQid() {
		return qid;
	}
	

	public void setQtext(String qtext) {
		this.qtext = qtext;
	}
	public String getQtext() {
		return qtext;
	}

	
	public void setOpt1(String opt1) {
		this.opt1 = opt1;
	}
	public String getOpt1() {
		return opt1;
	}


	public void setOpt2(String opt2) {
		this.opt2 = opt2;
	}
	public String getOpt2() {
		return opt2;
	}


	public void setOpt3(String opt3) {
		this.opt3 = opt3;
	}
	public String getOpt3() {
		return opt3;
	}


	public void setOpt4(String opt4) {
		this.opt4 = opt4;
	}
	public String getOpt4() {
		return opt4;
	}


	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	
}
