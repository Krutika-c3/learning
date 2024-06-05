package com.application.example.service;

import com.application.example.entity.Address;
import com.application.example.entity.Student;

import com.application.example.repository.graphQL.StudentRepository;
import com.application.example.request.CreateStudentRequest;
import com.application.example.request.CreateSubjectRequest;
import com.application.example.request.UpdateStudentRequest;
import com.application.example.request.UpdateSubjectRequest;
import com.application.example.response.StudentResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {

	/*@Autowired
	AddressRepository addressRepository;

	@Autowired
	SubjectRepository subjectRepository;
*/
	@Autowired
	StudentRepository studentRepository;

	public Student getStudentById(String id) {
		return studentRepository.findById(id);
	}

	public Student createStudent (CreateStudentRequest createStudentRequest) {
		Student student = new Student(createStudentRequest);

	/*	Address address = new Address();
		address.setStreet(createStudentRequest.getStreet());
		address.setCity(createStudentRequest.getCity());

		address = addressRepository.save(address);

		student.setAddress(address);*/


		student = studentRepository.save(student);


		/*Set<Subject> subjectSet = new HashSet<>();

		if(createStudentRequest.getSubjectsLearning() != null) {
			for (CreateSubjectRequest createSubjectRequest :
					createStudentRequest.getSubjectsLearning()) {
				Subject subject = new Subject();
				subject.setSubjectName(createSubjectRequest.getSubjectName());
				subject.setMarksObtained(createSubjectRequest.getMarksObtained());
			*//*	Set<Student> students = new HashSet<>();
				students.add(student);*//*
				subject.setStudent(student);
				subjectSet.add(subject);
			}
			subjectRepository.saveAll(subjectSet);
		}
		student.setLearningSubjects(subjectSet);*/
		return student;
	}

	public Boolean deleteStudent(String id){
		//Student student = studentRepository.getOne(id);


	/*	Integer addressId = student.getAddress().getId();
		Set<Integer> subjectIds = student.getLearningSubjects().stream().map(subject -> subject.getId()).collect(Collectors.toSet());
		subjectIds.forEach(subjectId -> subjectRepository.deleteById(subjectId));*/


		studentRepository.deleteById(id);


		/*addressRepository.deleteById(addressId);*/
		return true;
	}

	public Student updateStudent(UpdateStudentRequest updateStudentRequest) {

		//Student student = studentRepository.getOne(updateStudentRequest.getId());


		/*student.setFirstName(updateStudentRequest.getFirstName());
		student.setLastName(updateStudentRequest.getLastName());
		student.setEmail(updateStudentRequest.getEmail());

		Address address = student.getAddress();
		address.setStreet(updateStudentRequest.getStreet());
		address.setCity(updateStudentRequest.getCity());

		addressRepository.saveAndFlush(address);

		student.setAddress(address);*/


		Student student = studentRepository.saveAndFlush(updateStudentRequest);


		/*Set<Subject> subjectsList = new HashSet<>();

		if(updateStudentRequest.getSubjectsLearning() != null) {
			for (UpdateSubjectRequest updateSubjectRequest :
					updateStudentRequest.getSubjectsLearning()) {
				Student finalStudent = student;
				student.getLearningSubjects().forEach(subject1 -> {
					subject1.setSubjectName(updateSubjectRequest.getSubjectName());
					subject1.setMarksObtained(updateSubjectRequest.getMarksObtained());
				*//*	Student student1 = new Student();
					student1.setFirstName(updateStudentRequest.getFirstName());
					student1.setLastName(updateStudentRequest.getLastName());
					student1.setEmail(updateStudentRequest.getEmail());*//*
					subject1.setStudent(finalStudent);
					subject1 = subjectRepository.saveAndFlush(subject1);
					subjectsList.add(subject1);
					System.out.println("SubjectRepo " +subject1.getSubjectName() + " , Marks " + subject1.getMarksObtained());
				});
				*//*Subject subject = new Subject();
				subject.setSubjectName(updateSubjectRequest.getSubjectName());
				subject.setMarksObtained(updateSubjectRequest.getMarksObtained());
				subject.setStudent(student);
				subjectsList.add(subject);
				subjectRepository.saveAndFlush(subject);*//*
			}
		}
		student.setLearningSubjects(subjectsList);*/

		return student;
		//return null;
	}

	public List<StudentResponse> getStudents(){
		//List<Student> students =
		return studentRepository.getStudents();
				/*stream().map(student ->
			new StudentResponse(student)).collect(Collectors.toList());*/
	}
}
