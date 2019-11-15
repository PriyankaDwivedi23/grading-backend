package com.gradingapp.service;

import java.util.List;

import com.gradingapp.bean.GraderData;
import com.gradingapp.bean.StudentData;

public interface GraderService {
	
	public List<StudentData> getHomeworkSubmissions(String homeworkName);
	
	public GraderData getSubmissionFiles(String homeworkName, String questionName, String userName);
	
	public void submitGrades(GraderData data);

}
