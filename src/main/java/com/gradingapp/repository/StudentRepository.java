package com.gradingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gradingapp.model.StudentHomework;

	@Repository
	public interface StudentRepository extends MongoRepository<StudentHomework, String>{

	}
