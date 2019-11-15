package com.gradingapp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.gradingapp.bean.GraderData;
import com.gradingapp.bean.Student;
import com.gradingapp.bean.StudentData;

@Repository
@Qualifier("graderDao")
public class GraderDaoImpl implements GraderDao {
	
	@Autowired
    MongoTemplate mongoTemplate;

	@Override
	public List<StudentData> getHomeworkSubmissions(String homeworkName) {
		
		List<StudentData> studentDataList = new ArrayList<StudentData>(); 
		
		HashMap<String, List<String>> studentProblemMap = new HashMap<String, List<String>>();
		Query query = new Query(Criteria.where("homeworkName").is(homeworkName));
		List<Student> students = mongoTemplate.find(query, Student.class);
		for(Student student:  students){
			String userName = student.getUserName();
			String problemName = student.getQuestionName();
			String submissionDate = student.getLastModifiedDate();

			if(studentProblemMap.containsKey(userName)) {
				List<String> problemList = studentProblemMap.get(userName);
				problemList.add(problemName);
				studentProblemMap.put(userName, problemList);
			}else {
				List<String> problems = new ArrayList<String>();
				problems.add(problemName);
				studentProblemMap.put(userName, problems);
			}
		}
		
		for(Map.Entry<String, List<String>> entry: studentProblemMap.entrySet()) {
			studentDataList.add(getStudentData(entry.getKey(), entry.getValue()));
		}
		return studentDataList;
	}
	
	private StudentData getStudentData(String userName, List<String> problems) {
		StudentData studentData = new StudentData();
		studentData.setUserName(userName);
		studentData.setProblems(problems);
		return studentData;
	}

	@Override
	public GraderData getSubmissionFiles(String homeworkName, String questionName, String userName) {
		Query query = new Query(Criteria.
				where("homeworkName").is(homeworkName)
				.and("questionName").is(questionName)
				.and("userName").is(userName));
		
		
		
		Student result = mongoTemplate.findOne(query, Student.class);
		GraderData data = setGraderData(result);
		return data;
	}
	
	private GraderData setGraderData(Student student) {
		GraderData data = new GraderData();
		data.setHomeworkFileName("Main.java");
		data.setWriteupFileName("");
		data.setResult(student.getResult());
		return data;
	}

}
