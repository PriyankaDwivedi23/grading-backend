package com.gradingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradingapp.model.Problem;
import com.gradingapp.repository.ProblemRepository;



@Service
public class ProblemService {
	
	@Autowired
	private ProblemRepository problemRepository;
	
	//Create Homework
	public void upload(Problem problem) {
		problemRepository.save(problem);
	}
	

}
