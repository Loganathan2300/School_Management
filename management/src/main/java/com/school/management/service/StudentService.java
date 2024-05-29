package com.school.management.service;
//
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.entity.School;
import com.school.management.entity.Student;
import com.school.management.repository.SchoolRepository;
import com.school.management.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
    SchoolRepository schoolRepository;
	
	private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";


	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}
	
	public List<Student> retriveStudent() {
		return studentRepository.findAll() ;
	}
	
//	public String removeId(Long id) {
//		Optional<Student> removeDetails=studentRepository.findById(id);
//		if(removeDetails.isPresent()) {
//			studentRepository.deleteById(id);
//			return "Sucessfully Deleted....";
//		}else {
//			return "Data Not Found....";
//		}
//	}

	public Student retrieveStudentId(Long id) {
		return studentRepository.findById(id).orElse(null);
	}
	
	 public School addStudentToSchool(Long id, Student student) {
	        validateEmail(student.getEmail());
	        Optional<School> optionalSchool = schoolRepository.findById(id);
	        if (optionalSchool.isPresent()) {
	            School school = optionalSchool.get();
	            student.setSchool(school);
	            studentRepository.save(student);
	            return schoolRepository.save(school);
	        } else {
	            throw new RuntimeException("School not found");
	        }
	    }
	 
	 private void validateEmail(String email) {
	        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	        if (!pattern.matcher(email).matches()) {
	            throw new IllegalArgumentException("Invalid email format: " + email);
	        }
	    }

	public void deleteSchool(Long id) {
		studentRepository.deleteById(id);	
	}
	
}
