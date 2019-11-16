package com.gradingapp.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gradingapp.utils.FileUtils;

@Service("fileService")
public class FileServiceImpl implements FileService {
	
private static final String folderPath = "/Users/bebo/Documents/grading-backend/src/main/resources/uploads/";
    
    public ResponseEntity<?> handleFileUpload(MultipartFile file, String Type , String homeworkName, String questionName, String userName) {
        try {
        	File directory  = new File(FileUtils.generatePath(Type, homeworkName, questionName, userName));
        	if(!directory.exists()) {
        		directory.mkdirs();
        	}
        	
        	String finalPath = directory + "//" + FileUtils.generateFileName(Type);
        	
			Path path = Paths.get(finalPath);

        	try {
        		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        	
        	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(finalPath).toUriString();
        	System.out.println("File Download Uri: " + fileDownloadUri);

        } catch (Exception ioe) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
    
    public ResponseEntity handleFileUploads(MultipartFile[] files, String Type, String homeworkName, String questionName, String userName ) {
    	
        	for(MultipartFile file : files) {
        		ResponseEntity uploaded = handleFileUpload(file, Type, homeworkName, questionName, userName);
        	}    
        	return ResponseEntity.ok().build();
    }

	@Override
	public Resource downloadFile(String homeworkName, String questionName, String userName, String fileName) {
		
		String finalPath = FileUtils.generatePath("Student", homeworkName, questionName, userName);
		System.out.println("Final path: " + finalPath);
		
		try {
			Path path = Paths.get(finalPath).toAbsolutePath().normalize().resolve(fileName).normalize();
			System.out.println("Path URI: " + path.toUri());
			
			Resource resource = new UrlResource(path.toUri());
			System.out.println("Resource: " + resource);
			
			if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
			
		}catch(Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}

}
