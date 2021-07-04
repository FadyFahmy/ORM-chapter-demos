package com.in28minutes.database.databasedemo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.database.databasedemo.entity.Course;
import com.in28minutes.database.databasedemo.entity.Passport;
import com.in28minutes.database.databasedemo.entity.Student;

@Repository
@Transactional
public class StudentRepository {
	
	@Autowired
	EntityManager em;
	
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	
	public void deleteById(Long id) {
		Student Student = findById(id);
		em.remove(Student);
	}
	
	public void saveStudentWithPassport() {
		Passport passport = new Passport("123456");
		em.persist(passport);
		Student student = new Student("Mike", passport);
		
		em.persist(student);
	}
	
	public void insertStudentAndCourse(Student student, Course course) {
		student.addCourse(course);
		course.addStudent(student);
		
		em.persist(course);
		em.persist(student);

	}
}
