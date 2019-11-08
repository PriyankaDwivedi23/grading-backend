package com.gradingapp.bean;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student")
public class Student {
	
	String userName;
	String homeworkName;
	String questionName;
	
	public Student(String userName, String homeworkName, String questionName) {
		this.userName = userName;
		this.homeworkName = homeworkName;
		this.questionName = questionName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHomeworkName() {
		return homeworkName;
	}
	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

}
