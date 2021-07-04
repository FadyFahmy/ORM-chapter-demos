package com.in28minutes.database.databasedemo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.database.databasedemo.entity.Course;
import com.in28minutes.database.databasedemo.entity.Employee;
import com.in28minutes.database.databasedemo.entity.Review;

@Repository
@Transactional
public class EmployeeRepository {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EntityManager em;
	
	public void insert(Employee employee) {
		em.persist(employee);
	}
	
	public List<Employee> retrieveAllPartTimeEmployee() {
		return em.createQuery("select e from PartTimeEmployee e", Employee.class).getResultList();
	}
	
	public List<Employee> retrieveAllFullTimeEmployee() {
		return em.createQuery("select e from FullTimeEmployee e", Employee.class).getResultList();
	}
	
	public Employee findById(Long id) {
		return em.find(Employee.class, id);
	}
	
	
	
	
}
