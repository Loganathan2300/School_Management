package com.school.management.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "sequenceGenerater")
	private Long id; 
	
	@Column
	private String subject;
	
	@Column
	private String content;
	
	@Column
    private Integer points;  //question marks per id 
    
	@ManyToOne
    @JoinColumn(name = "teacher_id")
	@JsonBackReference
    private Teacher teacher;
	
	
	public Integer getPoints() {
		return points;
	}
	
	public void setPoints(Integer points) {
		this.points = points;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
