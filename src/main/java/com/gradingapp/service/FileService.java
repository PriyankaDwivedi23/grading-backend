package com.gradingapp.service;

import java.io.File;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
	
private static final String folderPath = "/Users/bebo/Documents/grading-backend/src/main/resources/uploads/";
    
    public ResponseEntity  handleFileUpload(MultipartFile file, String Type , String homeworkName, String questionName, String userName) {
        try {
        	File directory  = new File(generatePath(Type, homeworkName, questionName, userName));
        	if(!directory.exists()) directory.mkdirs();
        	String finalPath = directory + "/" + generateFileName(Type);
        	
        	System.out.println(finalPath);
            File fileToSave = new File(finalPath);
            file.transferTo(fileToSave);
        } catch (IOException ioe) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
    
    public String generateFileName(String Type) {
    	String fileName = "";
    	switch(Type) {
    	case "Student" :
    		fileName = "Main.java";
    		break;
    	case "Professor-Input":
    		fileName = "input.txt";
    		break;
    	case "Professor-Output":
    		fileName = "output.txt";
    		break;	
    	}
    	return fileName;
    	
    	
    }
    
    public String generatePath(String Type , String homeworkName, String questionName, String userName) {
    	String finalPath = folderPath;
    	String delimiter = "/";
    	switch(Type) {
    	case "Student" :
    		finalPath += Type + delimiter + userName + delimiter + homeworkName + delimiter + questionName + delimiter;
    		break;
    	case "Professor-Input":
    		finalPath += "Professor" + delimiter  + homeworkName + delimiter+ questionName + delimiter +  "inputFiles" + delimiter ;
    		break;
    	case "Professor-Output":
    		finalPath += "Professor" + delimiter  + homeworkName + delimiter + questionName + delimiter +  "outputFiles" + delimiter;
    		break;
    	case "Writeup" :
    		finalPath += "Student" + delimiter + homeworkName + delimiter + "writeup" + delimiter;
    		break;
    	}
    	return finalPath;
    }
    
    
    public ResponseEntity  handleFileUploads(MultipartFile[] files, String Type, String homeworkName, String questionName, String userName ) {
    	
        	for(MultipartFile file : files) {
        		ResponseEntity uploaded = handleFileUpload(file, Type, homeworkName, questionName, userName);
        	}    
        	return ResponseEntity.ok().build();
    }
}


