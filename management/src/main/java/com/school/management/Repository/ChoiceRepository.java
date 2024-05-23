package com.school.management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.management.Entity.Choice;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {

}
