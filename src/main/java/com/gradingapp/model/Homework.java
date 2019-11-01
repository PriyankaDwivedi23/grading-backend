package com.gradingapp.model;
import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Homework {
	
	@Id
	public ObjectId _id;
	String homeworkName;
	Date dueDate;
	Question[] question;
	
	

}
