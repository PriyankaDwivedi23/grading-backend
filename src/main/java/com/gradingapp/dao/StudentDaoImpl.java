package com.gradingapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.gradingapp.bean.Homework;
import com.gradingapp.bean.Student;

@Repository
@Qualifier("studentDao")
public class StudentDaoImpl implements StudentDao{
	
	
	@Autowired
    MongoTemplate mongoTemplate;
 
    final String COLLECTION = "studentHomework";
    
	@Override
	public void create(Student s) {
		
		mongoTemplate.insert(s);
		
	}

	@Override
	public void update(Student s) {
		mongoTemplate.save(s);
		
	}

	@Override
	public void delete(Student s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Student find(Student s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
