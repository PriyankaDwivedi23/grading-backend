package com.gradingapp.model;

public class Testcase {
	
	private String testCaseFile;
	private String expectedOutputFile;
	
	public String getTestCaseFile() {
		return testCaseFile;
	}
	public void setTestCaseFile(String testCaseFile) {
		this.testCaseFile = testCaseFile;
	}
	public String getExpectedOutputFile() {
		return expectedOutputFile;
	}
	public void setExpectedOutputFile(String expectedOutputFile) {
		this.expectedOutputFile = expectedOutputFile;
	}
}
