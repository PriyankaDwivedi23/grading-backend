package com.gradingapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value="/download", method=RequestMethod.GET) 
	public ResponseEntity<Object> downloadFile(@RequestParam("fileName") String filename) throws IOException  {
		FileWriter filewriter =  null;
		try {	
		
		File file = new File(filename);
		
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-disposition", "inline;filename=" + filename);
	    headers.setContentDispositionFormData(filename, filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
		return responseEntity;
		} catch (Exception e ) {
			return new ResponseEntity<>("error occurred", HttpStatus.INTERNAL_SERVER_ERROR);	
		} finally {
			if(filewriter!=null)
				filewriter.close();
		}
	}
	
	
	
//	@CrossOrigin
//	@GetMapping(value = "/downloadFile")
//	public ResponseEntity<Resource> downloadFile(@RequestParam("homeworkName") String homeworkName, 
//			@RequestParam("questionName")String questionName, @RequestParam("userName") String userName) {
//		System.out.println("Homework Name: " + homeworkName);
//		System.out.println("Question Name: " + questionName);
//		System.out.println("User Name: " + userName);
//		
//		Resource resource = fileService.downloadFile(homeworkName, questionName, userName, "Main.java");
//		
//		String contentType = "application/octet-stream";
//
//		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).
//				header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\\" +  resource.getFilename() + "\\").body(resource);
//	}
	
}
