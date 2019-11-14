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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gradingapp.bean.Homework;
import com.gradingapp.bean.Problem;
import com.gradingapp.service.HomeworkService;


@CrossOrigin("*")
@RestController
public class HomeworkController {
	
	@Autowired
	HomeworkService homeworkService;
	
    @CrossOrigin
	@PostMapping(value = "/create" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> create(@ModelAttribute Homework homework) {       
		homeworkService.create(homework);
		return new ResponseEntity<>("Successfully uploaded!", HttpStatus.OK);
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
    	
    	return new ResponseEntity<>(homeworkNames, HttpStatus.OK);
    }
    
    @CrossOrigin
	@GetMapping(value = "/findProblem")
    public ResponseEntity<?> find(@RequestParam("homeworkName") String homeworkName){
    	System.out.println("Homework name: " + homeworkName);
    	List<String> problemNames = new ArrayList<String>();
    	List<Problem> problems = homeworkService.findProblem(homeworkName);
    	for(Problem problem: problems) {
    		problemNames.add(problem.getProblemName());
    	}
    	return new ResponseEntity<>(problemNames, HttpStatus.OK);
    }
    
    @CrossOrigin
	@GetMapping(value = "/findAllHomework")
    public ResponseEntity<?> findAll(){
    	List<String> homeworkNames = new ArrayList<String>();
    	List<Homework> homeworks = homeworkService.findAll();
    	for(Homework homework: homeworks) {
    		homeworkNames.add(homework.getHomeworkName());
    	}
    	System.out.println("Homeworknames list: " + homeworkNames);
    	return new ResponseEntity<>(homeworkNames, HttpStatus.OK);
    }
}