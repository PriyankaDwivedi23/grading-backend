package com.gradingapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gradingapp.bean.Problem;
import com.gradingapp.service.FileService;
import com.gradingapp.service.HomeworkService;

@CrossOrigin("*")
@RestController
public class FileController {
	
	@Autowired
	FileService fileService;
	
	@Autowired
	HomeworkService homeworkService;
	
	@CrossOrigin
	@PostMapping(value = "/upload")
	public ResponseEntity<?> upload(MultipartFile inputFile, MultipartFile outputFile, Problem problem) {
		
		System.out.println(problem.getHomeworkName()+   "    " + problem.getProblemName() + "    "+  problem.getProblemDescription());
		
		fileService.handleFileUpload(inputFile, "Professor-Input", problem.getHomeworkName(), problem.getProblemName(), "");
		fileService.handleFileUpload(outputFile, "Professor-Output", problem.getHomeworkName(), problem.getProblemName(), "");
		
		homeworkService.updateProblem(new Problem(problem.getProblemName(), problem.getProblemDescription(),problem.getHomeworkName()));
		return new ResponseEntity<>("Successfully uploaded!", HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping(value = "/downloadFile")
	public ResponseEntity<Resource> downloadFile(String homeworkName, String questionName, String userName, String fileName) {
		System.out.println("Homework Name: " + fileName);
		System.out.println("Question Name: " + fileName);
		System.out.println("User Name: " + fileName);
		System.out.println("File Name: " + fileName);
		
		Resource resource = fileService.downloadFile(homeworkName, questionName, userName, fileName);
		
		String contentType = "application/octet-stream";

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).
				header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\\" +  resource.getFilename() + "\\").body(resource);
	}
}
