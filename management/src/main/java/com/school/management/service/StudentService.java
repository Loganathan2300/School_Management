package com.school.management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.school.management.dto.StudentDTO;
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
	
//	public List<StudentDTO> getStudents(String name, String email, String schoolName, Integer page, Integer size, String sortField, String sortDirection) {
//        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
//        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 6, sort);
//        
//        Page<Student> studentsPage;
//        if (name != null || email != null || schoolName != null) {
//            studentsPage = studentRepository.searchStudents(name, email, schoolName, pageable);
//        } else {
//            studentsPage = studentRepository.findAll(pageable);
//        }
//
//        List<StudentDTO> studentDTOs = new ArrayList<>();
//        for (Student student : studentsPage) {
//            StudentDTO dto = new StudentDTO();
//            dto.setId(student.getId());
//            dto.setName(student.getName());
//            dto.setEmail(student.getEmail());
//            dto.setSchoolName(student.getSchool().getName());
//            studentDTOs.add(dto);
//        }
//        return studentDTOs;
//    }
	
	public List<StudentDTO> getStudents(String search, Integer page, Integer size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 6, sort);

        
           Page<Student> studentsPage = studentRepository.searchStudents(search, pageable);
        List<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : studentsPage) {
            StudentDTO dto = new StudentDTO();
            dto.setId(student.getId());
            dto.setName(student.getName());
            dto.setEmail(student.getEmail());
            dto.setSchoolName(student.getSchool().getName());
            studentDTOs.add(dto);
        }
        return studentDTOs;
    }

    public List<Student> retrieveAllStudents() {
        return studentRepository.findAll();
    }
    
//	public List<Student> getStudentPagination(int page, int size) {
//		
//		Pageable pageable=PageRequest.of(page, size);
//		Page<Student> pageStudent=studentRepository.findAll(pageable);
//		
//		return pageStudent.getContent();
//	}
//	
//	public List<StudentDTO> searchStudents(String name, String email, String schoolName, int page, int size, String sortField, String sortDirection) {
//        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
//        Pageable pageable = PageRequest.of(page, size, sort);
//        Page<Student> studentsPage = studentRepository.searchStudents(name, email, schoolName, pageable);
//
//        List<StudentDTO> studentDTOs = new ArrayList<>();
//        for (Student student : studentsPage) {
//            StudentDTO dto = new StudentDTO();
//            dto.setId(student.getId());
//            dto.setName(student.getName());
//            dto.setEmail(student.getEmail());
//            dto.setSchoolName(student.getSchool().getName());
//            studentDTOs.add(dto);
//        }
//        return studentDTOs;
//    }
	
}
