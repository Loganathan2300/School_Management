package com.school.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{

	boolean existsByStudentIdAndQuestionId(Long studentId, Long questionId);
	List<Answer> findAllByStudentId(Long studentId);
}