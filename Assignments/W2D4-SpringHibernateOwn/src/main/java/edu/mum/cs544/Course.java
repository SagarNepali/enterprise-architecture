package edu.mum.cs544;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
public class Course {
	@Id
	private long coursenumber;
	private String name;
	private String grade;

	public Course(long coursenumber, String name, String grade) {
		this.coursenumber = coursenumber;
		this.name = name;
		this.grade = grade;
	}

	public Course() {
	}

	public long getCoursenumber() {
		return coursenumber;
	}

	public void setCoursenumber(long coursenumber) {
		this.coursenumber = coursenumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
