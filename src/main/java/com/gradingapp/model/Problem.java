package com.gradingapp.model;


public class Problem {
	
	private String problemName;
	private String problemDescription;
//	private Integer testCaseNum;
	private Test[] t;

	
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
//	public Integer getTestCaseNum() {
//		return testCaseNum;
//	}
//	public void setTestCaseNum(Integer testCaseNum) {
//		this.testCaseNum = testCaseNum;
//	}
	
	
	public Test[] getT() {
		return t;
	}
	public void setT(Test[] t) {
		this.t = t;
	}

}