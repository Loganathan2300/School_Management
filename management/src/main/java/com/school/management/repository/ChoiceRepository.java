package com.school.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.management.entity.Choice;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {


	@Query("SELECT c FROM Choice c WHERE " +
			   "(c.id IS NULL OR c.id =:id) OR "+
		       "(c.content IS NULL OR c.content LIKE %:content%)")
	List<Choice> searchChoiceDetails(@Param("id") Long id,@Param("content") String content);

}
