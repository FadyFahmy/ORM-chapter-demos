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

import com.in28minutes.database.databasedemo.entity.Course;
import com.in28minutes.database.databasedemo.entity.Review;
import com.in28minutes.database.databasedemo.repository.CourseRepository;

@SpringBootTest
class CourseRepositoryTests {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	void findById_basic() {
		Course course = repository.findById(10001L);
		assertEquals("jpa in 100 steps", course.getName());
	}
	
	@Test
	@Transactional
	void findById_first_level_cache_test() {
		Course course = repository.findById(10001L);
		logger.info("first course retrieved -> {}", course);
		Course course2 = repository.findById(10001L);
		logger.info("first course retrieved again -> {}", course2);
		assertEquals("jpa in 100 steps", course.getName());
		assertEquals("jpa in 100 steps", course2.getName());
	}
	
	@Test
	@DirtiesContext
	void deleteById_basic() {
		repository.deleteById(10002L);
		assertNull(repository.findById(10002L));
	}
	
	@Test
	@Transactional
	void retrieveReviewsForCourse() {
		Course course1 = repository.findById(10001L);
		logger.info("{}", course1.getReviews());
	}
	
	@Test
	@Transactional
	void retrieveCourseForReview() {
		Review review = em.find(Review.class, 50001L);
		logger.info("{}", review.getCourse());
	}
	
	@Test
	@DirtiesContext
	void save_basic() {
		Course course1 = repository.findById(10001L);
		assertEquals("jpa in 100 steps", course1.getName());
		course1.setName("jpa in 50 steps");
		repository.save(course1);
		Course course2 = repository.findById(10001L);
		assertEquals("jpa in 50 steps", course2.getName());
	}
	
	

}
