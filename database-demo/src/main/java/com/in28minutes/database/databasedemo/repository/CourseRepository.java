package com.in28minutes.database.databasedemo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.database.databasedemo.entity.Course;
import com.in28minutes.database.databasedemo.entity.Review;
import com.in28minutes.database.databasedemo.entity.ReviewRating;

@Repository
@Transactional
public class CourseRepository {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EntityManager em;
	
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}
	
	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}
	
	public Course save(Course course) {
		if (course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}
		return course;
	}
	
	public void addReviewForCourse(long courseId, List<Review> reviews) {
		Course course = findById(courseId);
		logger.info("reviews for a course -> {}", course.getReviews()); 
		
		Review review1 = new Review("Great hands-on stuff", ReviewRating.FIVE);
		Review review2 = new Review("good hands-on stuff", ReviewRating.FIVE);
		
		
		for(Review review: reviews) {
			course.addReview(review);
			review1.setCourse(course);
			em.persist(review);
		}
	}
	
	
}
