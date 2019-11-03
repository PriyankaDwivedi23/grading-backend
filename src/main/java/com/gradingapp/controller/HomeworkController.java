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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gradingapp.model.Homework;
import com.gradingapp.service.HomeworkService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class HomeworkController {
	
	@Autowired
	private HomeworkService homeworkService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeworkController.class);
	
	@CrossOrigin
	@PostMapping("/create")
	public ResponseEntity create(@ModelAttribute Homework homework) {
		System.out.println("Inside Homework controller");
		homeworkService.create(homework);
		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
	}

}