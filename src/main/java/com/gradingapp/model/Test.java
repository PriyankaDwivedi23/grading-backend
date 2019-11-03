package com.gradingapp.model;

import java.io.File;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;
@Document
public class Test {

	MultipartFile inputFile;
	MultipartFile outputFile;
	public MultipartFile getInputFile() {
		return inputFile;
	}
	public void setInputFile(MultipartFile inputFile) {
		this.inputFile = inputFile;
	}
	public MultipartFile getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(MultipartFile outputFile) {
		this.outputFile = outputFile;
	}
	

	
	
}
