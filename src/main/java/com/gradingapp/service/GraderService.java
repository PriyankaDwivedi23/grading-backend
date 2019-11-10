package com.gradingapp.service;

import java.util.List;

import com.gradingapp.bean.StudentData;

public interface GraderService {
	
	public List<StudentData> getHomeworkSubmissions(String homeworkName);

}
