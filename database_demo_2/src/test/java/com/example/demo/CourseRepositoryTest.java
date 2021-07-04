package com.example.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import entity.Address;
import entity.Course;
import entity.Review;
import entity.Student;
import repository.CourseRepository;
import repository.StudentRepository;


// questions to try to model
// 1. what if part of the aggregate is shared between to aggregate root ...
// example : truck and car share the same engine
// 2. could not using custom queries to access relation properties


@SpringBootTest
class CourseRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final String NAME = "learning Spring data jdbc";
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	
	private Course getCourse() {
		Set<Review> reviews = new HashSet<>();
		reviews.add(new Review(4, "this is really a wonderful course"));
		reviews.add(new Review(5, "what i enjoyed the most about this course is the organization"));
		
		return new Course(NAME, reviews);
	}

	@Test
	void testAddingReviews() {
		
		Course course = getCourse();
		
		/* check sql statements for saving this */
		courseRepository.save(course);
		
		/* get the same course by ID */
		logger.info("id for the course -> {}", course.getId());
		logger.info("executing find by id -> {}", courseRepository.findById(course.getId()));
	}
	
	@Test
	public void findByName() {
		
		Course course = getCourse();
		
		/* check sql statements for saving this */
		courseRepository.save(course);
		/* get the same course by ID */
		logger.info("finding a course by name -> {}", courseRepository.findByName(NAME));
	}
	
	@Test
	public void testOneToManyRelationsWithConditionsBuiltIn() {
		Course course = getCourse();
		courseRepository.save(course);
		logger.info("The courses with a specific description -> {}", 
		courseRepository.findByReviewDescriptionContaining("course"));
	}
	
	@Test
	public void addReviewForCourse() {
		Course course = courseRepository.findById(10001L).orElse(null);
		if (Objects.isNull(course)) return;
		course.addReview(new Review(2, "quite a bad course"));
		courseRepository.save(course);
	}
	
	@Test
	@Transactional
	public void changeCourseName() {
		Course course = courseRepository.findById(10001L).orElse(null);
		if (Objects.isNull(course)) return;
		course.setName(course.getName() + "-Updated");
		courseRepository.save(course);
	}
	
	@Test
	public void testAddingStudentToCourse() {
		Student student = new Student("new student", 
				new Address("dummt line1", "dummy line 2", "dummy city address"));
		
		studentRepository.save(student);
		logger.info("saved student id -> {}", student.getId());
		Course course = new Course("mastering many to many relations", new HashSet<>());
		course.addStudent(student);
		courseRepository.save(course);
	}
	
	@Test
	public void getStudentsForEachCourse() {
		for (Course c: courseRepository.findAll()) {
			logger.info("comple info for course -> {}", c.getName());
			logger.info("student ids for the course -> {}", c.getStudentsIds());
			Iterable<Student> registeredStudents = studentRepository.findAllById(c.getStudentsIds()); 
			logger.info("registered students for the course -> {}", registeredStudents);
		}
	}
	
	@Test
	public void getCoursesForEachStudent() {
		for(Student s: studentRepository.findAll()) {
			logger.info("List of all courses for student -> {}", s);
			Set<Course> courses = courseRepository.findByStudentId(s.getId());
			logger.info("number of courses for given student is -> {}", courses.size());
			for(Course c: courses) {
				logger.info("course: {}", c);
			}
		}
	}

}
