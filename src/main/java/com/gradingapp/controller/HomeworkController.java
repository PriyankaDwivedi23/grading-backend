package com.gradingapp.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gradingapp.service.FileService;
import com.gradingapp.service.HomeworkService;
import com.gradingapp.bean.Problem;
import com.gradingapp.bean.Homework;


@CrossOrigin("*")
@RestController
public class HomeworkController {
	
	@Autowired
	HomeworkService homeworkService;
	
	@Autowired
	FileService fileService;
	
    @CrossOrigin
	@PostMapping(value = "/create" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity create(@ModelAttribute Homework homework) {       
		homeworkService.create(homework);
		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
	}
    
    @CrossOrigin
	@PostMapping(value = "/upload")
	public ResponseEntity upload(MultipartFile inputFile, MultipartFile outputFile, Problem problem) {
		
		System.out.println(problem.getHomeworkName()+   "    " + problem.getProblemName() + "    "+  problem.getProblemDescription());
		
		fileService.handleFileUpload(inputFile, "Professor-Input", problem.getHomeworkName(), problem.getProblemName(), "");
		fileService.handleFileUpload(outputFile, "Professor-Output", problem.getHomeworkName(), problem.getProblemName(), "");
		
		homeworkService.updateProblem(new Problem(problem.getProblemName(), problem.getProblemDescription(),problem.getHomeworkName()));
		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
	}
    
    @CrossOrigin
	@GetMapping(value = "/findAll")
    public List<Homework> findAll(){
    	System.out.println("Inside find All controller");
    	return homeworkService.findAll();
    }
    
    @CrossOrigin
	@GetMapping(value = "/findProblem")
    public List<Problem> find(String homeworkName){
    	System.out.println("Inside find controller");
    	return homeworkService.findProblem(homeworkName);
    }
}