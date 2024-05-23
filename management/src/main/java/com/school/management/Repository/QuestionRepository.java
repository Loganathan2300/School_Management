package com.school.management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.management.Entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
