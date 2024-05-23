package com.school.management.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="answer")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "sequenceGenerater")
	private Long id; 
	
	@Column
	private boolean answers;
	
	 @ManyToOne
	 @JoinColumn(name = "student_id")
	 private Student student;

	 @ManyToOne
	 @JoinColumn(name = "question_id")
	 private Question question;

	 @ManyToOne
	 @JoinColumn(name = "choice_id")
	 private Choice choice;

	public boolean getAnswers() {
		return answers;
	}

	public void setAnswers(boolean answers) {
		this.answers = answers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Choice getChoice() {
		return choice;
	}

	public void setChoice(Choice choice) {
		this.choice = choice;
	} 
	
}
