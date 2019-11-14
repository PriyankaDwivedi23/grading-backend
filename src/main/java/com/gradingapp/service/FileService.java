package com.gradingapp.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
	
	public ResponseEntity<?> handleFileUpload(MultipartFile file, String Type , String homeworkName, String questionName, String userName);
	
	public Resource downloadFile(String homeworkName, String questionName, String userName, String fileName);
	
	

}


