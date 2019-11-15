package com.gradingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gradingapp.bean.GraderData;
import com.gradingapp.bean.Student;
import com.gradingapp.bean.StudentData;
import com.gradingapp.service.GraderService;

@RestController
public class GraderController {
	
	@Autowired
	private GraderService graderService;
	
	@Autowired
	private FileController fileController;
	
	@CrossOrigin
	@GetMapping(value = "/getHomeworkSubmissions")
	public ResponseEntity<?> getHomeworkSubmissions(@RequestParam("homeworkName") String homeworkName){
		List<StudentData> studentData = graderService.getHomeworkSubmissions(homeworkName);
		return new ResponseEntity<>(studentData, HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping(value = "/getSubmissionFiles")
	public ResponseEntity<?> getSubmissionFiles(@RequestParam("homeworkName") String homeworkName,
			@RequestParam("questionName") String questionName, @RequestParam("userName") String userName){
		
		System.out.println("Student Name: "+ homeworkName);
		System.out.println("Prob name: "+ questionName);
		System.out.println("User name: "+ userName);
		
		GraderData graderData = graderService.getSubmissionFiles(homeworkName, questionName, userName);
		
//		ResponseEntity<Resource> resource = fileController.downloadFile(student.getHomeworkName(),
//				student.getQuestionName(), student.getUserName());
		
		return new ResponseEntity<>(graderData, HttpStatus.OK);
	} 
}
