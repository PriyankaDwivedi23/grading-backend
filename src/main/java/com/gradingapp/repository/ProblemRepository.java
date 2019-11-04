package com.gradingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gradingapp.model.Problem;

@Repository
public interface ProblemRepository extends MongoRepository<Problem,String> {

}
