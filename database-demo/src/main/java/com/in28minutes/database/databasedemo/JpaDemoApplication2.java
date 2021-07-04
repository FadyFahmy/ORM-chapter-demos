package com.in28minutes.database.databasedemo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.database.databasedemo.entity.Course;
import com.in28minutes.database.databasedemo.entity.FullTimeEmployee;
import com.in28minutes.database.databasedemo.entity.PartTimeEmployee;
import com.in28minutes.database.databasedemo.entity.Review;
import com.in28minutes.database.databasedemo.entity.Student;
import com.in28minutes.database.databasedemo.repository.CourseRepository;
import com.in28minutes.database.databasedemo.repository.EmployeeRepository;
import com.in28minutes.database.databasedemo.repository.StudentRepository;

@SpringBootApplication
public class JpaDemoApplication2 implements CommandLineRunner{
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication2.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
