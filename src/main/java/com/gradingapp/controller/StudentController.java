package com.gradingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gradingapp.model.StudentHomework;
import com.gradingapp.service.StudentService;

@CrossOrigin("*")
@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@CrossOrigin
	@PostMapping(value = "/submitHomework")
	public ResponseEntity submitHomework(MultipartFile sourceCode, StudentHomework studentHomework) {
		
		System.out.println(sourceCode.getSize());
		System.out.println(studentHomework.getUserName()+ studentHomework.getHomeworkName() + studentHomework.getQuestionName());
		
		studentService.submitHomework(sourceCode, new StudentHomework(studentHomework.getUserName(), studentHomework.getHomeworkName() , studentHomework.getQuestionName()));
		
		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
	}

}
