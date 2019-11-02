package com.gradingapp.model;

import com.gradingapp.model.Testcase;

public class Problem {
	
	private String problemName;
	private String problemDescription;
	private Integer testCaseNum;
	private Testcase[] testCases;
	
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
	public Integer getTestCaseNum() {
		return testCaseNum;
	}
	public void setTestCaseNum(Integer testCaseNum) {
		this.testCaseNum = testCaseNum;
	}
	
	public Testcase[] getTestcases() {
		return testCases;
	}
	public void setTestcases(Testcase[] testcases) {
		this.testCases = testcases;
	}
}