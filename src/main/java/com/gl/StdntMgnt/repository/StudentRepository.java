package com.gl.StdntMgnt.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.StdntMgnt.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	Set<Student> findAllByFirstName(String firstName);
}
