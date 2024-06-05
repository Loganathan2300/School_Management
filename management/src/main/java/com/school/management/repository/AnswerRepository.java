package com.school.management.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.school.management.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{

	boolean existsByStudentIdAndQuestionId(Long studentId, Long questionId);
	List<Answer> findAllByStudentId(Long studentId);
//	Page<Answer> searchTeachers(@Param("keyword") String keyword, Pageable pageable);
}
