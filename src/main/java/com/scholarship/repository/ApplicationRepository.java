package com.scholarship.repository;

import com.scholarship.model.Application;
import com.scholarship.model.Student;
import com.scholarship.model.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
	List<Application> findByStudent(Student student);

	Application findByStudentAndScholarship(Student student, Scholarship scholarship);

	List<Application> findByScholarship(Scholarship scholarship);
}