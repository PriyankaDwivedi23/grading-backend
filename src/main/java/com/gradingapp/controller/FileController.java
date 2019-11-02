package com.gradingapp.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;


import org.slf4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

	private static final String folderPath = "/Users/bebo/Documents/grading-backend/src/main/resources/uploads/";
    
    public ResponseEntity  handleFileUpload(MultipartFile file, String Type , String homeworkName, String questionName, String userName) {
        try {
        	String finalPath  = folderPath+ file.getOriginalFilename();
        	System.out.println(finalPath);
            File fileToSave = new File(finalPath);
            file.transferTo(fileToSave);
        } catch (IOException ioe) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
    
    public  String generatePath(String Type , String homeworkName, String questionName, String userName) {
    	String finalPath = folderPath;
    	String delimiter = "/";
    	switch(Type) {
    	case "Student" :
    		finalPath += Type + delimiter + userName + delimiter + homeworkName + delimiter + questionName + delimiter;
    		break;
    	case "Professor-Input":
    		finalPath += "Professor" + delimiter + userName + delimiter + homeworkName + delimiter+ questionName + delimiter +  "inputFiles" + delimiter ;
    		break;
    	case "Professor-Output":
    		finalPath += "Professor" + delimiter + userName + delimiter + homeworkName + delimiter + questionName + delimiter +  "outputFiles" + delimiter;
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