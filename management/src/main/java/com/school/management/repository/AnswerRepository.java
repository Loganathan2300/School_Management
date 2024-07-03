package com.school.management.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.management.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{

	boolean existsByStudentIdAndQuestionId(Long studentId, Long questionId);
	List<Answer> findAllByStudentId(Long studentId);

	@Query("SELECT a FROM Answer a WHERE " +
		       "(a.answers IS NULL OR a.answers=:answers%) OR " +
		       "(a.student IS NULL OR a.student=:student) OR"+
		       "(a.question IS NULL OR a.question=:question) OR"+
		       "(a.choice IS NULL OR a.choice=:choice)" )
	List<Answer> searchAnswers(@Param("answers") Boolean answers,@Param("student") Long student,@Param("question") Long question,@Param("choice") Long choice);
	@Query("SELECT a FROM Answer a WHERE " +
		       "(a.answers IS NULL OR a.answers=:answers%) OR " +
		       "(a.student IS NULL OR a.student=:student) OR"+
		       "(a.question IS NULL OR a.question=:question) OR"+
		       "(a.choice IS NULL OR a.choice=:choice)" )
	Page<Answer> searchStudents(@Param("search")String search, Pageable pageable);
}
