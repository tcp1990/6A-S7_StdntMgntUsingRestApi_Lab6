package com.gl.StdntMgnt.service;

import java.util.Set;

import com.gl.StdntMgnt.model.Student;

public interface StudentService {

	Student saveStudent(Student student);

	Set<Student> fetchAllStudents();

	Student fetchStudentById(int studentId);

	void deleteStudentById(int studentId);
}
