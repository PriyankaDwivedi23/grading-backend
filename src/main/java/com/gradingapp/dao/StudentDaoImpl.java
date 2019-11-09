package com.gradingapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import com.gradingapp.bean.Homework;
import com.gradingapp.bean.Student;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;




@Repository
@Qualifier("studentDao")
public class StudentDaoImpl implements StudentDao{
	
	
	@Autowired
    MongoTemplate mongoTemplate;
 
    final String COLLECTION = "student";
    
	@Override
	public void create(Student s) {

		Query query = new Query(Criteria 
	            .where("userName").is(s.getUserName())
	            .and("homeworkName").is(s.getHomeworkName())
	            .and("questionName").is(s.getQuestionName()));
		
		List<Student> students =  mongoTemplate.find(query, Student.class);
		for(Student student : students) {
//			student.s
		}
		if(students.isEmpty()) {
			mongoTemplate.save(s);
		}

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
