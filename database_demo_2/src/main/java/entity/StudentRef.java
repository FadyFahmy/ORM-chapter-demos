package entity;

//import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("COURSE_STUDENT")
public class StudentRef {
	
//	@Column("STUDENT_ID")
	private Long student;
	
	public StudentRef(Long student) {
		this.student = student;
	}

	public Long getStudentId() {
		return student;
	}

	public void setStudentId(Long student) {
		this.student = student;
	}
}
