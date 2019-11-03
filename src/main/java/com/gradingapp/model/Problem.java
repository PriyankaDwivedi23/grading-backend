package com.gradingapp.model;

import org.springframework.web.multipart.MultipartFile;

public class Problem {
	
	private String problemName;
	private String problemDescription;
	private MultipartFile inputFile;
	private MultipartFile outputFile;
	private String homeworkName;
	
	public MultipartFile getInputFile() {
		return inputFile;
	}
	public void setInputFile(MultipartFile inputFile) {
		this.inputFile = inputFile;
	}
	public MultipartFile getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(MultipartFile outputFile) {
		this.outputFile = outputFile;
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