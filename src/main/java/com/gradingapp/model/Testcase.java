package com.gradingapp.model;

import org.springframework.web.multipart.MultipartFile;

public class Testcase {
	
	private String test;
	private MultipartFile[] testCaseFile;
	private MultipartFile[] expectedOutputFile;
	
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	
	public MultipartFile[] getTestCaseFile() {
		return testCaseFile;
	}
	public void setTestCaseFile(MultipartFile[] testCaseFile) {
		this.testCaseFile = testCaseFile;
	}
	
	public MultipartFile[] getExpectedOutputFile() {
		return expectedOutputFile;
	}
	public void setExpectedOutputFile(MultipartFile[] expectedOutputFile) {
		this.expectedOutputFile = expectedOutputFile;
	}
}
