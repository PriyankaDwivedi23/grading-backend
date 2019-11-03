package com.gradingapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gradingapp.model.Homework;
import com.gradingapp.model.Problem;

@Repository
public interface HomeworkRepository extends MongoRepository<Homework, String> {
	public static final MongoTemplate mongoTemplate = null;

    
    public default void updateProblem(Problem h) {

    	Update update = new Update();
    	update.addToSet("problems", h);
    	Criteria criteria = Criteria.where("homeworkName").is(h.getHomeworkName());
//    	MongoTemplate mongoTemplate = new MongoTemplate();
    	mongoTemplate.updateFirst(Query.query(criteria), update, "grading-app.homework");
    }

	public Homework findByHomeworkName(String homeworkName);

}