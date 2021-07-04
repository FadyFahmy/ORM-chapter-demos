package com.example.demo;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import entity.Address;
import entity.Course;
import entity.Student;
import repository.CourseRepository;
import repository.StudentRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Iterable<Course> allCourses = repository.findAll();
//		
//		logger.info("Available courses: ");
//		allCourses.forEach(course -> logger.info("courses -> {}", course ));
//		
//		Student student = new Student("new student", 
//				new Address("dummt line1", "dummy line 2", "dummy city address"));
		
//		studentRepository.save(student);
//		logger.info("saved student id -> {}", student.getId());
//		Course course = new Course("mastering many to many relations", new HashSet<>());
//		course.addStudent(student);
//		courseRepository.save(course);
		
//		for (Course c: courseRepository.findAll()) {
//			logger.info("comple info for course -> {}", c.getName());
//			logger.info("student ids for the course -> {}", c.getStudentsIds());
//			Iterable<Student> registeredStudents = studentRepository.findAllById(c.getStudentsIds()); 
//			logger.info("registered students for the course -> {}", registeredStudents);
//		}
		
	}

}
