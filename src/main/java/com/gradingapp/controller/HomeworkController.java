package com.gradingapp.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gradingapp.model.Homework;
import com.gradingapp.service.HomeworkService;

@RestController
public class HomeworkController {
	
	@Autowired
	private HomeworkService homeworkService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeworkController.class);
	
	@ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Homework homework) {
		homeworkService.create(homework);
	}
}