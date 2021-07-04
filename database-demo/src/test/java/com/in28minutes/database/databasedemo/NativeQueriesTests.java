package com.in28minutes.database.databasedemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.in28minutes.database.databasedemo.entity.Course;
import com.in28minutes.database.databasedemo.repository.CourseRepository;

@SpringBootTest
class NativeQueriesTests {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	void nativeQuery_basic() {
		List resultList = em.createNativeQuery("SELECT * FROM COURSE Where is_deleted = false", Course.class).getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}
	
	@Test
	void native_queries_with_parameter() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE id = ?", Course.class);
		query.setParameter(1, 10001L);
		List resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE WHERE id = ? -> {}", resultList);
	}
	
	@Test
	void native_queries_with_named_parameter() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE id = :id", Course.class);
		query.setParameter("id", 10001L);
		List resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE WHERE id = ? -> {}", resultList);
	}
	
	@Test
	@Transactional
	void native_queries_to_update() {
		Query query = em.createNativeQuery("UPDATE COURSE set last_updated_date=sysdate()");
		int noOfRowsUpdated = query.executeUpdate();
		logger.info("noOfRowsUpdated -> {}", noOfRowsUpdated);
	}

	
}
