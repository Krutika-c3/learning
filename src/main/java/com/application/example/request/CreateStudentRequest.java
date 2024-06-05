package com.application.example.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
public class CreateStudentRequest {

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private Integer address_id;
	private List<String> learningSubjects;

}
