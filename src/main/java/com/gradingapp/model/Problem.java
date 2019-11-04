package com.gradingapp.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@Document
public class Problem {
	
	private String problemName;
	private String problemDescription;
	private String homeworkName;
	
	public Problem(String problemName, String problemDescription, String homeworkName ) {
		this.problemName = problemName;
		this.problemDescription = problemDescription;
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
	public String getHomeworkName() {
		return homeworkName;
	}
	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}

}