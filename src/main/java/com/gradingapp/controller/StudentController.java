package com.gradingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gradingapp.bean.Student;

import com.gradingapp.service.FileService;
import com.gradingapp.service.StudentService;

@CrossOrigin("*")
@RestController
public class StudentController {
	
	
	@Autowired
	private StudentService studentService;
	@Autowired
	FileService fileService;
	
	@CrossOrigin
	@PostMapping(value = "/submitHomework")
	public ResponseEntity submitHomework(MultipartFile sourceCode, Student studentHomework) {
		
		if(sourceCode != null) {
			fileService.handleFileUpload(sourceCode, "Student", studentHomework.getHomeworkName(), studentHomework.getQuestionName(), studentHomework.getUserName());
		}
		
		System.out.println(studentHomework.getUserName()+ studentHomework.getHomeworkName() + studentHomework.getQuestionName());
		studentService.create(new Student(studentHomework.getUserName(), studentHomework.getHomeworkName() , studentHomework.getQuestionName()));
		
		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping(value = "/uploadWriteup")
	public ResponseEntity uploadWriteup(MultipartFile writeupFile, Student student) {
		if(null != writeupFile) {
			fileService.handleFileUpload(writeupFile, "Writeup", student.getHomeworkName(), "", student.getUserName());
		}
		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
	}
	
 
}
