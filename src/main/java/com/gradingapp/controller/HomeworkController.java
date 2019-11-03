package com.gradingapp.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gradingapp.model.Homework;
import com.gradingapp.model.Problem;
import com.gradingapp.service.HomeworkService;


@CrossOrigin("*")
@RestController
public class HomeworkController {
	
	@Autowired
	private HomeworkService homeworkService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeworkController.class);
	
	@CrossOrigin
	@PostMapping(value = "/create" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity create(@ModelAttribute Homework homework) {
		homeworkService.create(homework);
		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping(value = "/upload" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity upload(@ModelAttribute Problem problem) {
		homeworkService.upload(problem);
		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
	} 
}