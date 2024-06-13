package com.school.management.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.management.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	@Query("SELECT t FROM Teacher t WHERE " +
		       "(t.name IS NULL OR t.name LIKE %:name%) OR " +
		       "(t.subject IS NULL OR t.subject LIKE %:subject%)")
	List<Teacher> searchTeacher(String name, String subject, Pageable pageable);

}
