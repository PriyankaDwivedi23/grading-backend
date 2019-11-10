package com.gradingapp.dao;

import java.util.List;

import com.gradingapp.bean.StudentData;

public interface GraderDao {

	public List<StudentData> getHomeworkSubmissions(String homeworkName);
}
