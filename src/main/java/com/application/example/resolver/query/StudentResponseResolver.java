package com.application.example.resolver.query;

import com.application.example.response.StudentResponse;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Service;

@Service
public class StudentResponseResolver implements GraphQLResolver<StudentResponse> {
	
	public String getFullName (StudentResponse studentResponse) {
		return studentResponse.getFirstName() + " " + studentResponse.getLastName();
	}
}
