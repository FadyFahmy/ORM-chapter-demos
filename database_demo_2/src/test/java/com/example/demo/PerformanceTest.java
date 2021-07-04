package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import repository.CourseRepository;

@SpringBootTest
public class PerformanceTest {
	
	@Autowired
	CourseRepository courseRepository;
	
	@Test
	public void testLvlOneCache() {
		courseRepository.findById(10001L);
		courseRepository.findById(10001L);
		courseRepository.findById(10001L);
		courseRepository.findById(10001L);
		courseRepository.findById(10001L);
	}
}
