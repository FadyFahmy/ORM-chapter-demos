package com.in28minutes.database.databasedemo;


import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.database.databasedemo.entity.Course;
import com.in28minutes.database.databasedemo.repository.CourseSpringDataRepository;

@SpringBootTest
class PerformanceTuningTest {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	EntityManager em;
	
	@Autowired
	CourseSpringDataRepository courseSpringDataRepository;
	
	@Test
	@Transactional
	public void testLvlOneCache() {
		courseSpringDataRepository.findById(10001L);
		courseSpringDataRepository.findById(10001L);
		courseSpringDataRepository.findById(10001L);
		courseSpringDataRepository.findById(10001L);
		courseSpringDataRepository.findById(10001L);
	}
	
	@Test
	@Transactional
	public void creatingN1Problem() {
		List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class).getResultList();
		for (Course course: courses) {
			logger.info("course for student {} -> {}", course, course.getStudents());
		}
	}
	
	@Test
	@Transactional
	public void solvingN1Problem_entity_graph() {
		EntityGraph<Course> graph = em.createEntityGraph(Course.class);
		graph.addSubgraph("students");
		
		List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class)
				.setHint("javax.persistance.loadgraph", graph)
				.getResultList();
		for (Course course: courses) {
			logger.info("course for student {} -> {}", course, course.getStudents());
		}
	}
	
	@Test
	@Transactional
	public void solvingN1Problem_joint_fetch() {
		EntityGraph<Course> graph = em.createEntityGraph(Course.class);
		graph.addSubgraph("students");
		
		List<Course> courses = em.createNamedQuery("query_get_all_courses_join_fetch", Course.class)
				.setHint("javax.persistance.loadgraph", graph)
				.getResultList();
		for (Course course: courses) {
			logger.info("course for student {} -> {}", course, course.getStudents());
		}
	}
}
