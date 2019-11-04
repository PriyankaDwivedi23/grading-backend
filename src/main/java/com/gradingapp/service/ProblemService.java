package com.gradingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gradingapp.model.Problem;
import com.gradingapp.repository.ProblemRepository;



@Service
public class ProblemService {
	
	@Autowired
	private ProblemRepository problemRepository;
	
	@Autowired
	private FileService fileService;
	
	//Create Homework
	public void upload(MultipartFile inputFile, MultipartFile outputFile, Problem problem) {
		
		fileService.handleFileUpload(inputFile, "Professor-Input", problem.getHomeworkName(), problem.getProblemName(), "");
		fileService.handleFileUpload(outputFile, "Professor-Output", problem.getHomeworkName(), problem.getProblemName(), "");
		
		problemRepository.save(problem);
	}
}
