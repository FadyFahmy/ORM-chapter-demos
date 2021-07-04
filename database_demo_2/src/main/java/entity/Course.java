package entity;


import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.MappedCollection;

import lombok.AllArgsConstructor;

public class Course {
	
	@Id
	private Long id;
	
	private String name;
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	private LocalDateTime lastModifiedDate;
	
	private Set<Review> reviews;
	
//	@MappedCollection(idColumn = "COURSE_ID")
	private Set<StudentRef> students = new HashSet<>();
	

	public Set<Long> getStudentsIds() {
		return students.stream().map(StudentRef::getStudentId).collect(Collectors.toSet());
	}


	public Course(String name, Set<Review> reviews) {
		this.name = name;
		this.reviews = reviews;
	}
	
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public LocalDateTime getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}



	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}



	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}



	public Set<Review> getReviews() {
		return reviews;
	}



	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
	
	public void addStudent(Student student) {
		this.students.add(new StudentRef(student.getId()));
	}

	
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", createdDate=" + createdDate + ", lastModifiedDate="
				+ lastModifiedDate + ", reviews=" + reviews + ", students=" + students + "]";
	}


	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	
	
	
}
