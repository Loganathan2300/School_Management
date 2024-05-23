package com.school.management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.management.Entity.School;


@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

}
