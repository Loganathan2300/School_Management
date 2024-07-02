package com.school.management.dto;

public class StudentScore {

	private Long studentId;
    private int totalMark;

    public StudentScore(Long studentId, int totalMark) {
        this.studentId = studentId;
        this.totalMark = totalMark;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public int getScore() {
        return totalMark;
    }

    public void setScore(int totalMark) {
        this.totalMark = totalMark;
    }
}
