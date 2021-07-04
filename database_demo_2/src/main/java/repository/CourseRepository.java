package repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import entity.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
	
	List<Course> findByName(String name);
	
	// this is not working
//	List<Course> findDISTINCTByReviewsDescriptionContaining(String partOfDescription);
	
	@Query("SELECT DISTINCT c.* FROM course c JOIN review r "
			+ "ON c.id = r.course WHERE r.description LIKE '%course%'")
	List<Course> findByReviewDescriptionContaining(String partOfDescription);
	
	@Query("SELECT * FROM course c JOIN review r "
			+ "ON c.id = r.course WHERE r.description LIKE '%course%'")
	List<Course> findByReviewDescriptionContainingEnhanced(String partOfDescription);
	
	@Query("select c.* from course c join course_student cs on c.id = cs.course where cs.student = :id")
	Set<Course> findByStudentId(@Param("id") Long id);
}
