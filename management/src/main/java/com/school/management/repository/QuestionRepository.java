package com.school.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.management.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

//	@Query("SELECT q FROM Question q WHERE "+
//	"(q.id IS NULL OR q.id LIKE %:id%) OR " +
//	"(q.name IS NULL OR q.name LIKE %:name%) OR " +
//	"(q.content IS NULL OR q.content LIKE %:content%) OR " +
//	"(q.points IS NULL OR q.points LIKE %:points%)")
//	List<Question> searchQuestion(@Param("id") Long id,@Param("name") String name,@Param("content") String content,@Param("points") int points);

}
