package com.gradingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import com.gradingapp.model.Homework;
import com.gradingapp.model.Problem;
import com.gradingapp.model.Testcase;
import com.gradingapp.repository.HomeworkRepository;

@Service
public class HomeworkService {
	
	@Autowired
	private HomeworkRepository homeworkRepository;
	private FileService fileService;
	
	public void create(Homework homework) {
		String homeworkName = homework.getHomeworkName();
		System.out.println("HW name: " + homeworkName);
		Problem[] problems = homework.getProblems();
		System.out.println("Problems: " + problems);
		for(Problem problem: problems) {
			String problemName = problem.getProblemName();
			System.out.println("Problem name: " + problemName);
			Testcase[] testcases  = problem.getTestcases();
			System.out.println("Test case: " + testcases);
			for(Testcase testcase: testcases) {
				MultipartFile[] inputFiles = testcase.getTestCaseFile();
				System.out.println("Input File: " + inputFiles[0].getName());
				MultipartFile[] outputFiles = testcase.getExpectedOutputFile();
				System.out.println("Output File: " + outputFiles[0].getName());
				fileService.handleFileUploads(inputFiles, "Professor-Input", homeworkName, problemName, "");
				fileService.handleFileUploads(outputFiles, "Professor-Output", homeworkName, problemName, "");
			}
		}
		homeworkRepository.save(homework);
	}
}