package com.gradingapp.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gradingapp.service.FileService;
import com.gradingapp.service.HomeworkService;
import com.gradingapp.bean.Problem;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
    @RequestMapping(value = "/availableHomework", method = RequestMethod.GET)
    public ResponseEntity<?> availableHomework(){
    	List<String> homeworkNames = new ArrayList<String>();
    	List<Homework> homeworks = homeworkService.availableHomework();
    	for(Homework homework: homeworks) {
    		homeworkNames.add(homework.getHomeworkName());
    	}
    	System.out.println("Homeworknames list: " + homeworkNames);
    	
    	return new ResponseEntity(homeworkNames, HttpStatus.OK);
    }
    
    @CrossOrigin
	@GetMapping(value = "/findProblem")
    @ResponseBody
    public ResponseEntity<?> find(String homeworkName){
    	List<String> problemNames = new ArrayList<String>();
    	List<Problem> problems = homeworkService.findProblem(homeworkName);
    	for(Problem problem: problems) {
    		problemNames.add(problem.getProblemName());
    	}
    	return new ResponseEntity(problemNames, HttpStatus.OK);
    }
}