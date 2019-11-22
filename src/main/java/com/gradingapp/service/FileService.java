package com.gradingapp.service;

import java.io.File;
import java.io.FileNotFoundException;
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

@Service
public class FileService {

	private static final String folderPath = "uploads/";

	public static String generateFileName(String Type) {
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
			finalPath += "Student" + delimiter + userName + delimiter + homeworkName + delimiter + "writeup" + delimiter;
			break;
		}
		return finalPath;
	}
	
	public ResponseEntity<?> handleFileUpload(MultipartFile file, String Type , String homeworkName, String questionName, String userName) {
		try {
			File directory  = new File(generatePath(Type, homeworkName, questionName, userName));
			if(!directory.exists()) {
				directory.mkdirs();
			}

			String finalPath = directory + "/" ;
			if(Type.equals("Writeup")) {
				deleteFile(finalPath);
				finalPath += file.getOriginalFilename();
			}else {
				finalPath += generateFileName(Type);
			}
			System.out.println(finalPath);

			Path path = Paths.get(finalPath);

			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

		} catch (Exception ioe) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok().build();
	}
	
	private void deleteFile(String path) {
		File file = new File(path);
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File f: files) {
				f.delete();
			}
		}
	}

	public ResponseEntity<?> handleFileUploads(MultipartFile[] files, String Type, String homeworkName, String questionName, String userName ) {

		for(MultipartFile file : files) {
			ResponseEntity<?> uploaded = handleFileUpload(file, Type, homeworkName, questionName, userName);
		}    
		return ResponseEntity.ok().build();
	}


	public Resource downloadFile(String homeworkName, String questionName, String userName, String fileName) {

		String finalPath = generatePath("Student", homeworkName, questionName, userName);
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


