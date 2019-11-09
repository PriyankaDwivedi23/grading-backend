package com.gradingapp.dao;

import java.util.List;

import com.gradingapp.bean.Student;

public interface StudentDao {
	
	public void create(Student s);
	 
    public void update(Student s);
 
    public void delete(Student s);
 
    public void deleteAll();
 
    public Student find(Student s);
 
    public List < Student > findAll();


}
