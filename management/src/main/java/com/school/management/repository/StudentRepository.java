package com.school.management.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.management.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long > {

//	@Query("SELECT s FROM Student s WHERE " +
//			   "(s.id IS NULL OR s.id =:id) OR "+
//		       "(s.name IS NULL OR s.name LIKE %:name%) OR " +
//		       "(s.email IS NULL OR s.email LIKE %:email%)")
//	List<Student> searchSchooldetails(@Param("id") Long id,@Param("name") String name,@Param("email") String email);
//	
//	@Query("SELECT s FROM Student s LEFT JOIN s.school school WHERE " +
//	           "(:name IS NULL OR s.name LIKE %:name%) AND " +
//	           "(:email IS NULL OR s.email LIKE %:email%) AND " +
//	           "(:schoolName IS NULL OR school.name LIKE %:schoolName%)")
//	    Page<Student> searchStudents(@Param("name") String name,@Param("email") String email,
//	    		@Param("schoolName") String schoolname,Pageable pageable);
	
	 @Query("SELECT s FROM Student s WHERE " +
	           "(:search IS NULL OR s.name LIKE %:search%) OR " +
	           "(:search IS NULL OR s.email LIKE %:search%) OR " +
	           "(:search IS NULL OR s.school.name LIKE %:search%)")
	    Page<Student> searchStudents(@Param("search") String search, Pageable pageable);
}
