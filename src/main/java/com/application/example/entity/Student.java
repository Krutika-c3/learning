package com.application.example.entity;

import com.application.example.request.CreateStudentRequest;
import com.application.example.request.UpdateStudentRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Student {
	private String firstName;
	private String lastName;
	private String email;
	private Integer address_id;
	private List<String> learningSubjects;

	public Student (CreateStudentRequest createStudentRequest) {
		this.firstName = createStudentRequest.getFirstName();
		this.lastName = createStudentRequest.getLastName();
		this.email = createStudentRequest.getEmail();
		this.address_id = createStudentRequest.getAddress_id();
		this.learningSubjects = createStudentRequest.getLearningSubjects();
	}

	public Student(UpdateStudentRequest updateStudentRequest) {
		this.firstName = updateStudentRequest.getFirstName();
		this.lastName = updateStudentRequest.getLastName();
		this.email = updateStudentRequest.getEmail();
		this.address_id = updateStudentRequest.getAddress_id();
		this.learningSubjects = updateStudentRequest.getLearningSubjects();
	}
}
