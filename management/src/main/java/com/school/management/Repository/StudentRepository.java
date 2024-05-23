package com.school.management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.management.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long > {

}
