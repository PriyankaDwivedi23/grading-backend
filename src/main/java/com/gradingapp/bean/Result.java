package com.gradingapp.bean;

public class Result {

	private int status;
	private String studentOutput;
	private String expectedOutput;
	private Boolean testCasePassed;
	
	public Result(int status, String studentOutput, String expectedOutput, Boolean testCasePassed) {
		this.status = status;
		this.studentOutput = studentOutput;
		this.expectedOutput = expectedOutput;
		this.testCasePassed = testCasePassed;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStudentOutput() {
		return studentOutput;
	}
	public void setStudentOutput(String studentOutput) {
		this.studentOutput = studentOutput;
	}
	public String getExpectedOutput() {
		return expectedOutput;
	}
	public void setExpectedOutput(String expectedOutput) {
		this.expectedOutput = expectedOutput;
	}
	public Boolean getTestCasePassed() {
		return testCasePassed;
	}
	public void setTestCasePassed(Boolean testCasePassed) {
		this.testCasePassed = testCasePassed;
	}
	
	
	
}
