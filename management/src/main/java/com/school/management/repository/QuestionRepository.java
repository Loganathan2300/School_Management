package com.school.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.management.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Query("SELECT q FROM Question q WHERE "+
	"(q.id IS NULL OR q.id = :id) OR " +
	"(q.subject IS NULL OR q.subject LIKE %:subject%) OR " +
	"(q.content IS NULL OR q.content LIKE %:content%) OR " +
	"(q.points IS NULL OR q.points = :points)")
	List<Question> searchQuestion(@Param("id") Long id,@Param("subject") String subject,@Param("content") String content,@Param("points") Integer points);
}
