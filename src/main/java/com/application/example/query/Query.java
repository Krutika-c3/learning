package com.application.example.query;

import com.application.example.request.SampleRequest;
import com.application.example.response.StudentResponse;
import com.application.example.service.StudentService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

	@Autowired
	StudentService studentService;

	public String firstQuery () {
		return "First Query";
	}

	public String secondQuery () {
		return "Second Query";
	}

	public String fullName (SampleRequest sampleRequest) {
		return sampleRequest.getFirstName() + " " + sampleRequest.getLastName();
	}

	public StudentResponse getStudent(String id) {
		return new StudentResponse(studentService.getStudentById(id));
	}

	public List<StudentResponse> getAllStudents(){
		return studentService.getStudents();
	}
}
