package com.gradingapp.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	public List<Homework> availableHomework() {
		DateFormat dateFormat =  new SimpleDateFormat("MM/dd/yyyy");
		String date = dateFormat.format(new Date());
		System.out.println("Today's date: " + date);
		
		Query query = new Query(Criteria.where("dueDate").gte(date));
		List<Homework> homeworks = mongoTemplate.find(query, Homework.class);
		System.out.println("Homeworks count: " + homeworks.size());
		return homeworks;
	}

	@Override
	public void updateProblem(Problem p) {
		Query query = new Query(Criteria.where("homeworkName").is(p.getHomeworkName()));
		Update updateCmd = new Update();
		updateCmd.addToSet("problem", p);
		mongoTemplate.updateFirst(query, updateCmd, Homework.class);
	}

	@Override
	public List<Problem> findProblem(String homeworkName) {
		List<Problem> problems = null;
		Query query = new Query(Criteria.where("homeworkName").is(homeworkName));
		Homework result = mongoTemplate.findOne(query, Homework.class);
		System.out.println("Homework: " + result);
		if(null != result) {
			problems = result.getProblem();
			System.out.println("Problems count: " + problems.size());
		}
		return problems;
	}

}
