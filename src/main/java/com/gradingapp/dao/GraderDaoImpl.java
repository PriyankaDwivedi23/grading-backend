package com.gradingapp.dao;

import java.util.ArrayList;
import java.util.List;

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
		
		Query query = new Query(Criteria.where("homeworkName").is(homeworkName));
		List<Student> students = mongoTemplate.find(query, Student.class);
		
		System.out.println("Result size: " + students.size());
		
		for(Student student: students){
			String userName = student.getUserName();
			
			boolean isUserExist = false;
			
			if(studentDataList != null && studentDataList.size() > 0) {
				for(StudentData studentData: studentDataList) {
					if(userName.equals(studentData.getUserName())) {
						studentData.getProblems().add(student.getQuestionName());
						studentData.getSubmissionDates().add(student.getLastModifiedDate());
						isUserExist = true;
					}
				}
				if(!isUserExist) {
					StudentData studentData = setStudentData(student);
					studentDataList.add(studentData);
				}
			}else {
				StudentData studentData = setStudentData(student);
				studentDataList.add(studentData);
			}
		}
		
		for(StudentData data: studentDataList) {
			System.out.println("User: " + data.getUserName());
			System.out.println("User prob: " + data.getProblems());
			System.out.println("User dates: " + data.getSubmissionDates());
		}
		
		return studentDataList;
	}
	
	private StudentData setStudentData(Student student) {
		StudentData studentData = new StudentData();
		studentData.setUserName(student.getUserName());
		studentData.getProblems().add(student.getQuestionName());
		studentData.getSubmissionDates().add(student.getLastModifiedDate());
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
