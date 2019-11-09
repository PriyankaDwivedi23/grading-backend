package com.gradingapp.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student")
public class Student {
	@Id
	String _id;
	String userName;
	String homeworkName;
	String questionName;

	
	public Student(String userName, String homeworkName, String questionName) {
		this.userName = userName;
		this.homeworkName = homeworkName;
		this.questionName = questionName;
//		this.result = 0;
	}
	
	public String getId() {
		return _id;
	}
	public void setId(String id) {
		this._id = id;
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
