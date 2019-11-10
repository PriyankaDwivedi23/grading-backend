package com.gradingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradingapp.bean.StudentData;
import com.gradingapp.dao.GraderDao;

@Service("graderService")
public class GraderServiceImpl implements GraderService {
	
	@Autowired
	GraderDao graderDao;

	@Override
	public List<StudentData> getHomeworkSubmissions(String homeworkName) {
		return graderDao.getHomeworkSubmissions(homeworkName);
	}

}
