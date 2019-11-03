package com.gradingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import com.gradingapp.model.Homework;
import com.gradingapp.model.Problem;
import com.gradingapp.model.Test;
import com.gradingapp.repository.HomeworkRepository;

@Service
public class HomeworkService {
	
	@Autowired
	private HomeworkRepository homeworkRepository;
	private FileService fileService;
	

	public void create(Homework homework) {
		String homeworkName = homework.getHomeworkName();
		System.out.println("HW name: " + homeworkName);
//		Problem[] problems = homework.getProblems();
//		System.out.println("Problems: " + problems);
//		for(Problem problem: problems) {
//			String problemName = problem.getProblemName();
//			System.out.println("Problem name: " + problemName);
//			System.out.println("testcase num : " + problem.getTestCaseNum());
//			Test[] t  = problem.getT();
//			for(Test t1: t) {
//				MultipartFile inputFiles = t1.getInputFile();
//				System.out.println("Input File: " + inputFiles.getOriginalFilename());
//				MultipartFile outputFiles = t1.getOutputFile();
//				System.out.println("Output File: " + outputFiles.getOriginalFilename());
//			}
			
//		}
//		homeworkRepository.save(homework);
	}
}