package com.school.management.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.management.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

//
//	@Query("SELECT s FROM Student s LEFT JOIN s.school school WHERE " +
//	           "(:name IS NULL OR s.name LIKE %:name%) AND " +
//	           "(:email IS NULL OR s.email LIKE %:email%) AND " +
//	           "(:schoolName IS NULL OR school.name LIKE %:schoolName%)")
	@Query("SELECT t FROM Teacher t " +
	           "LEFT JOIN t.school s " + // Assuming 'school' is the property name in Teacher entity referring to School entity
	           "WHERE (:name IS NULL OR t.name LIKE %:name%) " +
	           "AND (:subject IS NULL OR t.subject LIKE %:subject%) " +
	           "AND (:schoolName IS NULL OR s.name LIKE %:schoolName%)")
	    List<Teacher> searchTeacher(@Param("name") String name,@Param("subject") String subject,
	    		@Param("schoolName") String schoolName,Pageable pageable);
}
