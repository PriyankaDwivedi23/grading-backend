package com.gradingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gradingapp.model.Problem;
import com.gradingapp.service.ProblemService;

@CrossOrigin("*")
@RestController
public class ProblemController {
	
	@Autowired
	private ProblemService problemService;
	
	@CrossOrigin
	@PostMapping(value = "/upload")
	public ResponseEntity upload(MultipartFile inputFile, MultipartFile outputFile, Problem problem) {
		
		System.out.println(problem.getHomeworkName()+   "    " + problem.getProblemName() + "    "+  problem.getProblemDescription());
		
		problemService.upload(inputFile, outputFile, new Problem(problem.getProblemName(), problem.getProblemDescription(),problem.getHomeworkName()));
		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
	}
}
