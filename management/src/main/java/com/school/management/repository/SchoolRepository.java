package com.school.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.management.entity.School;


@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

	@Query("SELECT s FROM School s WHERE " +
	       "(s.name IS NULL OR s.name LIKE %:name%) OR " +
	       "(s.address IS NULL OR s.address LIKE %:address%)")
	List<School> searchSchooldetails( @Param("name") String name, @Param("address") String address);

}
