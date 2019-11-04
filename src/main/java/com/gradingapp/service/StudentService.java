package com.gradingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gradingapp.model.StudentHomework;
import com.gradingapp.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepoistory;
	
	@Autowired
	private FileService fileService;
	
	public void submitHomework(MultipartFile sourceCode, StudentHomework studentHomework) {
		
		System.out.println(sourceCode.getOriginalFilename());
		System.out.println(studentHomework.getUserName()+ studentHomework.getHomeworkName() + studentHomework.getQuestionName());
		
		fileService.handleFileUpload(sourceCode, "Student", studentHomework.getHomeworkName(), studentHomework.getQuestionName(),studentHomework.getUserName());
		studentRepoistory.save(new StudentHomework(studentHomework.getUserName(), studentHomework.getHomeworkName(), studentHomework.getQuestionName()));
		
	}

}
