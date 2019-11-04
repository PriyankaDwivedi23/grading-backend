package com.gradingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gradingapp.bean.Homework;
import com.gradingapp.bean.Problem;
import com.gradingapp.dao.HomeworkDao;

@Service("homeworkService")
public class HomeworkServiceImpl implements HomeworkService{
	
	@Autowired
	HomeworkDao homeworkDao;

	@Override
	public void create(Homework h) {
		// TODO Auto-generated method stub
		homeworkDao.create(h);
	}

	@Override
	public void update(Homework h) {
		// TODO Auto-generated method stub
		homeworkDao.update(h);
	}

	@Override
	public void delete(Homework h) {
		// TODO Auto-generated method stub
		homeworkDao.delete(h);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		homeworkDao.deleteAll();
	}

	@Override
	public Homework find(Homework h) {
		// TODO Auto-generated method stub
		return homeworkDao.find(h);
	}

	@Override
	public List<Homework> findAll() {
		// TODO Auto-generated method stub
		return homeworkDao.findAll();
	}

	@Override
	public void updateProblem(Problem p) {
		// TODO Auto-generated method stub
		homeworkDao.updateProblem(p);
		
	}

}
