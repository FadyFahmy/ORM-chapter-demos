package repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
	
}
