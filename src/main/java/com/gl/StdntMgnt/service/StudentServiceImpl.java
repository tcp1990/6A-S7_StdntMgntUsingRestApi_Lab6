package com.gl.StdntMgnt.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.StdntMgnt.model.Student;
import com.gl.StdntMgnt.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public Student saveStudent(Student student) {
		return this.studentRepository.save(student);
	}

	public Set<Student> fetchAllStudents() {
		return new HashSet<>(this.studentRepository.findAll());
	}

	public Student fetchStudentById(int studentId) {
		return this.studentRepository.findById(studentId).orElseThrow();
	}

	public void deleteStudentById(int studentId) {
		this.studentRepository.deleteById(studentId);
	}
}
