package com.application.springRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student save(Student user) {
        return studentRepository.save(user);
    }

    public Optional<Student> findOne(int id) {
        return studentRepository.findOne(id);
    }

    public Optional<Student> deleteById(int id) {
        return studentRepository.deleteById(id);
    }

    public Optional<Student> updateById(int id, Student student){
        return studentRepository.updateById(id, student);
    }
}

