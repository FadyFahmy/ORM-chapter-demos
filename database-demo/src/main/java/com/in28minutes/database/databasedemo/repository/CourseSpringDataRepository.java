package com.in28minutes.database.databasedemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.in28minutes.database.databasedemo.entity.Course;

@RepositoryRestResource(path="courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
	
	List<Course> findByName(String name);
	List<Course> countByName(String name);
	List<Course> findByNameAndId(String name, Long Id);
	List<Course> findByNameOrderByIdDesc(String name);
	List<Course> deleteByName(String name);
	
	List<Course> findByReviewsDescriptionContaining(String description);
	
	@Query("Select c From Course c where name like '100'")
	List<Course> coursesWith100();
	
	@Query(value="Select * From Course c where name like '100'", nativeQuery=true)
	List<Course> courseWith100Native();
	
//	@Query(name="query_get_100_steps_courses")
//	List<Course> courseWith100NamedQuery();
}
