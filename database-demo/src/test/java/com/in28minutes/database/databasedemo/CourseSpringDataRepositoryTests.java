package com.in28minutes.database.databasedemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.annotation.DirtiesContext;

import com.in28minutes.database.databasedemo.entity.Course;
import com.in28minutes.database.databasedemo.entity.Review;
import com.in28minutes.database.databasedemo.entity.ReviewRating;
import com.in28minutes.database.databasedemo.repository.CourseRepository;
import com.in28minutes.database.databasedemo.repository.CourseSpringDataRepository;

@SpringBootTest
class CourseSpringDataRepositoryTests {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final String NAME = "learning Spring data JPA";
	
	private Course getCourse() {
		List<Review> reviews = new ArrayList<>();
		reviews.add(new Review("this is really a wonderful course", ReviewRating.FOUR));
		reviews.add(new Review("what i enjoyed the most about this course is the organization", ReviewRating.FIVE));
		
		Course course = new Course(NAME);
		course.setReviews(reviews);
		return course;
	}
	
	@Autowired
	CourseSpringDataRepository repository;
	
	
	@Test
	public void find_by_id_course_present() {
		Optional<Course> courseOptional = repository.findById(10001L);
		assertTrue(courseOptional.isPresent());
	}
	
	@Test
	public void find_by_id_course_not_present() {
		Optional<Course> courseOptional = repository.findById(10011L);
		assertFalse(courseOptional.isPresent());
	}
	
	@Test
	public void testAddingReview() {
		Course course = getCourse();
		
		repository.save(course);
		
		/* get the same course by ID */
		logger.info("id for the course -> {}", course.getId());
		logger.info("executing find by id -> {}", repository.findById(course.getId()));
	}
	
	@Test
	@Transactional
	public void testOneToManyRelationsWithConditionsBuiltIn() {
		Course course = getCourse();
		logger.info("before saving the course");
		repository.save(course);
		logger.info("After saving the course");
		logger.info("The courses with a specific description -> {}", 
		repository.findByReviewsDescriptionContaining("100"));
	}
	
	@Test
	@Transactional
	public void addReviewForCourse() {
		Course course = repository.findById(10001L).orElse(null);
		if (Objects.isNull(course)) return;
		course.addReview(new Review("quite a bad course", ReviewRating.TWO));
		repository.saveAndFlush(course);
	}
	
	@Test
	@Transactional
	public void changeCourseName() {
		Course course = repository.findById(10001L).orElse(null);
		if (Objects.isNull(course)) return;
		course.setName(course.getName() + "-Updated");
		repository.saveAndFlush(course);
	}
	
	@Test
	public void pagination() {
		PageRequest pageRequest = PageRequest.of(0, 3);
		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("first page -> {}", firstPage.getContent());
		Pageable pageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(pageable);
		logger.info("second page -> {}", secondPage.getContent());
	}
	
	@Test
	public void find_by_name() {
		logger.info("find by name -> {}", repository.findByName("JPA in 50 steps"));
	}
	
//	@Test
//	@Transactional
//	public void testPropertyChaining() {
//		repository.findByReviewsDescription("JPA");
//	}

}
