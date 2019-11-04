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
import com.gradingapp.bean.Problem;

@Repository
@Qualifier("homeworkDao")
public class HomeworkDaoImpl implements HomeworkDao{
	
	@Autowired
    MongoTemplate mongoTemplate;
 
    final String COLLECTION = "homework";

	@Override
	public void create(Homework h) {
		String homeworkName = h.getHomeworkName();
		String dueDate = h.getDueDate();
		List<Problem> problems = new ArrayList<>();
		h.setProblem(problems);
		mongoTemplate.insert(h);
	}

	@Override
	public void update(Homework h) {
		// TODO Auto-generated method stub
		mongoTemplate.save(h);
	}

	@Override
	public void delete(Homework h) {
		// TODO Auto-generated method stub
		mongoTemplate.remove(h);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Homework find(Homework h) {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public List<Homework> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProblem(Problem p) {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("homeworkName").is(p.getHomeworkName()));
		Update updateCmd = new Update();
		updateCmd.addToSet("problem", p);
		mongoTemplate.updateFirst(query, updateCmd, Homework.class);
//        Homework h = mongoTemplate.findOne(query, Homework.class, COLLECTION);
//        System.out.print(h.getHomeworkName());
        
		
		
	}

}
