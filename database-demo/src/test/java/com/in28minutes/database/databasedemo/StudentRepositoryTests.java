package com.in28minutes.database.databasedemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.in28minutes.database.databasedemo.entity.Address;
import com.in28minutes.database.databasedemo.entity.Passport;
import com.in28minutes.database.databasedemo.entity.Student;
import com.in28minutes.database.databasedemo.repository.StudentRepository;

@SpringBootTest
class StudentRepositoryTests {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	StudentRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	@Transactional
	public void retrieveStudentAndPassportDetails() {
		Student student = em.find(Student.class, 20001L);
		logger.info("student -> {}" + student);
		logger.info("passport -> {}" + student.getPassport()); 
	}
	
	@Test
	@Transactional
	public void retrievePassportAndAssociatedStudent() {
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("passport -> {}" + passport);
		logger.info("student -> {}" + passport.getStudent()); 
	}
	
	@Test
	@Transactional
	public void retrieveStudentAndCourses() {
		Student student = em.find(Student.class, 20001L);
		logger.info("student -> {}", student);
		logger.info("student -> {}", student.getCourses());
		
	}
	
	@Test
	@Transactional
	public void setAddressDetails() {
		Student student = em.find(Student.class, 20001L);
		student.setAddress(new Address("line1", "line2", "city"));
		em.flush();
		logger.info("student -> {}", student);
		logger.info("student -> {}", student.getCourses());
		
	}
	
	@Test
	@Transactional
	public void someTest() {
		// database operation 1 - retrieve student
		Student student = em.find(Student.class, 20001L);
		
		// database operation 2 - retrieve password
		Passport passport = student.getPassport();
		
		// database operation 3 - update passport
		passport.setNumber("E123459");
		
		// database operation 4 - set name
		student.setName("new name");
	}
}
