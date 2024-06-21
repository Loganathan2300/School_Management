package com.school.management.dto;

public class StudentScore {

	private Long studentId;
    private int score;

    public StudentScore(Long studentId, int score) {
        this.studentId = studentId;
        this.score = score;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
