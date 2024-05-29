package com.school.management.dto;

public class QuestionDTO {

	private Long id;
    private String subject;
    private String content;
    private int points;
    
//    public QuestionDTO(Long id,String subject,String content,int points) {
//    	this.id=id;
//    	this.subject=subject;
//    	this.content=content;
//    	this.points=points;
//    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
    
    
}
