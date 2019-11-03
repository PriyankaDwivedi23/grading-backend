package com.gradingapp.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Homework {
	
	private String homeworkName;
	private String dueDate;
	private List<Problem> problem;
	
	
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
	
	public List<Problem> getProblem() {
		return problem;
	}
	public void setProblem(List<Problem> problem) {
		this.problem = problem;
	}
}
