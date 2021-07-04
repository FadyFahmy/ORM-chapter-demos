package com.in28minutes.database.databasedemo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.database.databasedemo.entity.Course;
import com.in28minutes.database.databasedemo.entity.Student;

@SpringBootTest
class JPQLTest {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	void jpql_basic() {
		List resultList = em.createNamedQuery("query_get_all_courses").getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}
	
	@Test
	void jpql_typed() {
		TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}
	
	@Test
	void jpql_where() {
		TypedQuery<Course> query = em.createNamedQuery("query_get_100_step_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c where name like '%100 Steps -> {}", resultList);
	}
	
	@Test
	void jpql_courses_without_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Result -> {}", resultList);
	}
	
	@Test
	void jpql_courses_with_atleats_2_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Result -> {}", resultList);
	}
	
	@Test
	void jpql_courses_order_by_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Result -> {}", resultList);
	}
	
	@Test
	void jpql_students_with_passport_with_a_certain_pattern() {
		TypedQuery<Student> query = em.createQuery("Select s from Student s where s.passport.number like '1234' ", Student.class);
		List<Student> resultList = query.getResultList();
		logger.info("Result -> {}", resultList);
	}
	
	@Test
	void join() {
		Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
		List<Object[]> queryList = query.getResultList();
		for(Object[] result: queryList) {
			logger.info("Course -> {}", result[0]);
			logger.info("Student -> {}", result[1]);
		}
	}
	
	@Test
	void left_join() {
		Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> queryList = query.getResultList();
		for(Object[] result: queryList) {
			logger.info("Course -> {}", result[0]);
			logger.info("Student -> {}", result[1]);
		}
	}
	
	@Test
	void cross_join() {
		Query query = em.createQuery("Select c, s from Course c, Student s");
		List<Object[]> queryList = query.getResultList();
		for(Object[] result: queryList) {
			logger.info("Course -> {}", result[0]);
			logger.info("Student -> {}", result[1]);
		}
	}
	
}
