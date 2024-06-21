package com.school.management.dto;

public class AnswerDTO {

	private Long id;
    private boolean answers;
    private StudentDTO student;
    private QuestionDTO question;
    private ChoiceDTO choice;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isAnswers() {
		return answers;
	}
	public void setAnswers(boolean answers) {
		this.answers = answers;
	}
	public StudentDTO getStudent() {
		return student;
	}
	public void setStudent(StudentDTO student) {
		this.student = student;
	}
	public QuestionDTO getQuestion() {
		return question;
	}
	public void setQuestion(QuestionDTO question) {
		this.question = question;
	}
	public ChoiceDTO getChoice() {
		return choice;
	}
	public void setChoice(ChoiceDTO choice) {
		this.choice = choice;
	}
    
}
