package com.gradingapp.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Homework {
	
	@Id
//	private ObjectId _id;
	private String homeworkName;
	private String dueDate;
	private List<Problem> problems;
	
//	public ObjectId get_id() {
//		return _id;
//	}
//	public void set_id(ObjectId _id) {
//		this._id = _id;
//	}
	
	public String getHomeworkName() {
		return homeworkName;
	}
	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}
	
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	public List<Problem> getProblems() {
		return problems;
	}
	public void setProblems(List<Problem> problems) {
		this.problems = problems;
	}
}
