package com.gradingapp.utils;

public class FileUtils {
	
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
    
    public static String generatePath(String Type , String homeworkName, String questionName, String userName) {
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
    		finalPath += "Student" + delimiter + userName +delimiter + homeworkName + delimiter + "writeup" + delimiter;
    		break;
    	}
    	return finalPath;
    }
}
