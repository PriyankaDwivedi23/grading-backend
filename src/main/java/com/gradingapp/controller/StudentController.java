package com.gradingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gradingapp.model.StudentHomework;
import com.gradingapp.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	
	@PostMapping("/submitHomework")
	public ResponseEntity submitHomework(MultipartFile sourceCode, StudentHomework studentHomework) {
		
		studentService.submitHomework(sourceCode, studentHomework);
		
		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
	}

}
