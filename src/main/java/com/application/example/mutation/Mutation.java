package com.application.example.mutation;

import com.application.example.request.CreateStudentRequest;
import com.application.example.request.UpdateStudentRequest;
import com.application.example.response.StudentResponse;
import com.application.example.service.StudentService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mutation implements GraphQLMutationResolver {

	@Autowired
	StudentService studentService;

	public StudentResponse createStudent (CreateStudentRequest createStudentRequest) {
		return new StudentResponse(studentService.createStudent(createStudentRequest));
	}

	public Boolean deleteStudent (String id) {
		return studentService.deleteStudent(id);
	}

	public StudentResponse updateStudent(UpdateStudentRequest updateStudentRequest){
		return new StudentResponse(studentService.updateStudent(updateStudentRequest));
	}
}
