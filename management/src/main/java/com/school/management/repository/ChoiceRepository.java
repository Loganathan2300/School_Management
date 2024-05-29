package com.school.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.management.entity.Choice;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {

}
