package com.school.management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.Entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
