package com.application.example.response;

import com.application.example.entity.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class StudentResponse {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private Integer address_id;
	private List<String> learningSubjects;

	// this is for internal use. DO NOT PUT IN SCHEMA
	private Student student;
	private String fullName;
	public StudentResponse (Student student) {
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.email = student.getEmail();
		this.address_id = student.getAddress_id();
		this.learningSubjects = student.getLearningSubjects();
	}
}
