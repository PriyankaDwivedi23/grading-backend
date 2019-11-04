package com.gradingapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradingapp.model.Homework;
import com.gradingapp.model.Problem;
import com.gradingapp.repository.HomeworkRepository;

@Service
public class HomeworkService {
	
	@Autowired
	private HomeworkRepository homeworkRepository;
	
	
	public void create(Homework homework) {
		String homeworkName = homework.getHomeworkName();
		String dueDate = homework.getDueDate();
		List<Problem> problems = new ArrayList<>();
		homework.setProblem(problems);
		homeworkRepository.save(homework);
	}
	
}