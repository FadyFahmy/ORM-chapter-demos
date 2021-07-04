package com.in28minutes.database.databasedemo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.database.databasedemo.entity.Course;
import com.in28minutes.database.databasedemo.entity.Student;

@SpringBootTest
class CriteriaQueriesTests {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	void criteria_basic() {
		// 1. use criteria builder
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		
		//2. define roots for tables which is involved
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		
		// 3. fetch results
		List<Course> resultList = em.createQuery(criteriaQuery.select(courseRoot)).getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}
	
	@Test
	void criteria_all_course_having_100_steps() {
		// 1. use criteria builder
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		
		//2. define roots for tables which is involved
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		
		// 3. define predicate
		Predicate like = criteriaBuilder.like(courseRoot.get("name"), "100 steps"); 
		
		// 4. adding predicate to criteria query
		criteriaQuery.where(like);
		
		// 5. fetch results
		List<Course> resultList = em.createQuery(criteriaQuery.select(courseRoot)).getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}
	
	@Test
	void all_courses_without_students() {
		// 1. use criteria builder
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		
		//2. define roots for tables which is involved
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		
		// 3. define predicate
		Predicate empty = criteriaBuilder.isEmpty(courseRoot.get("students")); 
		
		// 4. adding predicate to criteria query
		criteriaQuery.where(empty);
		
		// 5. fetch results
		List<Course> resultList = em.createQuery(criteriaQuery.select(courseRoot)).getResultList();
		logger.info("empty courses -> {}", resultList);
	}
	
	@Test
	void join() {
		// 1. use criteria builder
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		
		//2. define roots for tables which is involved
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		
		// 3. define predicate
		Join<Object, Object> join = courseRoot.join("students");
		
		// 4. adding predicate to criteria query
		
		// 5. fetch results
		List<Course> resultList = em.createQuery(criteriaQuery.select(courseRoot)).getResultList();
		logger.info("joined results -> {}", resultList);
	}
	
	@Test
	void left_join() {
		// 1. use criteria builder
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		
		//2. define roots for tables which is involved
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		
		// 3. define predicate
		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);
		
		// 4. adding predicate to criteria query
		
		// 5. fetch results
		List<Course> resultList = em.createQuery(criteriaQuery.select(courseRoot)).getResultList();
		logger.info("joined results -> {}", resultList);
	}
	
	
}
