package com.gradingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradingapp.model.Homework;
import com.gradingapp.repository.HomeworkRepository;

@Service
public class HomeworkService {
	
	@Autowired
	private HomeworkRepository homeworkRepository;
	
	public void create(Homework homework) {	
		//To do: file upload changes
		homeworkRepository.save(homework);
	}
}