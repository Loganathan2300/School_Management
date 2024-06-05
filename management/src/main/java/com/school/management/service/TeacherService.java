package com.school.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.school.management.entity.School;
import com.school.management.entity.Teacher;
import com.school.management.repository.SchoolRepository;
import com.school.management.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	SchoolRepository schoolRepository;

	public Teacher createTeacher( Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	public List<Teacher> retriveTeachers() {
		return teacherRepository.findAll();
	}

	public Teacher retriveTeacher(Long id) {
		return teacherRepository.findById(id).orElse(null);
	}
	
	public School addteacherToSchool(Long schoolId, Teacher teacher) {
        Optional<School> optionalSchool = schoolRepository.findById(schoolId);
        if (optionalSchool.isPresent()) {
            School school = optionalSchool.get();
            teacher.setSchool(school);
            teacherRepository.save(teacher);
            return schoolRepository.save(school);
        } else {
            throw new RuntimeException("School not found");
        }
    }

//	public String removeData(Long id) {
//		Optional<Teacher> optionalSchool = teacherRepository.findById(id);
//		if(optionalSchool.isPresent()) {
//			teacherRepository.deleteById(id);
//			return "School Deleted..";
//		}else {
//			return "school_details not found...";
//		}
//	}

	public void deleteTeacherId(Long id) {
		teacherRepository.deleteById(id);
	}

	public List<Teacher> getPaginatedTeacher(int page, int size) {
		
		Pageable pageable = PageRequest.of(page, size);
        Page<Teacher> pagedTeachers = teacherRepository.findAll(pageable);
        return pagedTeachers.getContent();
	}

	public List<Teacher> getSearchTeacher(String name, String subject) {
		
		return teacherRepository.searchTeacher(name,subject);
	}
	
}
