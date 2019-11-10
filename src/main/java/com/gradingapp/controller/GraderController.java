package com.gradingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gradingapp.bean.StudentData;
import com.gradingapp.service.GraderService;

@RestController
public class GraderController {
	
	@Autowired
	private GraderService graderService;
	
	@CrossOrigin
	@GetMapping(value = "/getHomeworkSubmissions")
	public ResponseEntity<?> getHomeworkSubmissions(@RequestParam("homeworkName") String homeworkName){
		List<StudentData> studentData = graderService.getHomeworkSubmissions(homeworkName);
		return new ResponseEntity<>(studentData, HttpStatus.OK);
	}
}
