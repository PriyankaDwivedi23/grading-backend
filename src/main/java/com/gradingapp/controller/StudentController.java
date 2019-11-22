package com.gradingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gradingapp.bean.Result;
import com.gradingapp.bean.Student;
import com.gradingapp.bean.Writeup;
import com.gradingapp.service.CompileAndRunService;
import com.gradingapp.service.FileService;
import com.gradingapp.service.StudentService;

@CrossOrigin("*")
@RestController
public class StudentController {
	
	
	@Autowired
	private StudentService studentService;
	@Autowired
	FileService fileService;
	@Autowired
	CompileAndRunService c;
	
	@CrossOrigin
	@PostMapping(value = "/submitHomework")
	public ResponseEntity<?> submitHomework(MultipartFile sourceCode, Student studentHomework) {
		String studentCodePath = "";
		Result result = new Result();
		System.out.println(studentHomework.getUserName()+ studentHomework.getHomeworkName() + studentHomework.getQuestionName());
		if(sourceCode != null) {
			fileService.handleFileUpload(sourceCode, "Student", studentHomework.getHomeworkName(), studentHomework.getQuestionName(), studentHomework.getUserName());
			String inputFilePath = fileService.generatePath("Professor-Input", studentHomework.getHomeworkName(), studentHomework.getQuestionName(), "") +"input.txt";
			String outputFilePath = fileService.generatePath("Professor-Output", studentHomework.getHomeworkName(), studentHomework.getQuestionName(), "") +"output.txt";
			studentCodePath = fileService.generatePath("Student", studentHomework.getHomeworkName(), studentHomework.getQuestionName(), studentHomework.getUserName());
			result = c.compileAndRun(studentCodePath, inputFilePath, outputFilePath);
		}
		studentService.create(new Student(studentHomework.getUserName(), studentHomework.getHomeworkName(), studentHomework.getQuestionName(), result, studentCodePath, ""));
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping(value = "/uploadWriteup")
	public ResponseEntity<?> uploadWriteup(MultipartFile writeupFile, Writeup writeup) {
		String writeupPath = "";
		if(null != writeupFile) {
			writeupPath = fileService.generatePath("Writeup", writeup.getHomeworkName(), "", writeup.getUserName()) + writeupFile.getOriginalFilename();;
			fileService.handleFileUpload(writeupFile, "Writeup", writeup.getHomeworkName(), "", writeup.getUserName());
			writeup.setWriteupURL(writeupPath);
			studentService.updateWriteup(writeup);
			
			System.out.println("Write up path: " + writeupPath);
		}
		return new ResponseEntity<>("Successfully uploaded!", HttpStatus.OK);
	}
}
