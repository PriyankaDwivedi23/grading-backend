package com.gradingapp.bean;

public class Problem {
	
	String problemName;
	String problemDescription;
	String homeworkName;
	
	public Problem(String problemName, String problemDescription, String homeworkName) {
		this.problemName = problemName;
		this.problemDescription = problemDescription;
		this.homeworkName = homeworkName;
	}
	public String getHomeworkName() {
		return homeworkName;
	}
	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}
	public String getProblemName() {
		return problemName;
	}
	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}
	public String getProblemDescription() {
		return problemDescription;
	}
	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}
	

}
