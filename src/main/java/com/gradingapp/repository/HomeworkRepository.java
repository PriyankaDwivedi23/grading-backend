package com.gradingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gradingapp.model.Homework;

@Repository
public interface HomeworkRepository extends MongoRepository<Homework, String> {
	
}