package com.school.management.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.management.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {


//	@Query("SELECT s FROM Student s LEFT JOIN s.school school WHERE " +
//	           "(:name IS NULL OR s.name LIKE %:name%) AND " +
//	           "(:email IS NULL OR s.email LIKE %:email%) AND " +
//	           "(:schoolName IS NULL OR school.name LIKE %:schoolName%)")
	
	@Query("SELECT t FROM Teacher t LEFT JOIN t.school s WHERE " + 
		       "(:search IS NULL OR t.name LIKE %:search%) " +
		       "OR (:search IS NULL OR t.subject LIKE %:search%) " +
		       "OR (:search IS NULL OR s.name LIKE %:search%)")
		Page<Teacher> searchTeacher(@Param("search") String search, Pageable pageable);
}
