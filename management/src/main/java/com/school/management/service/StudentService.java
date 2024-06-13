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

import com.school.management.DTO.StudentDTO;
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

	public List<Student> getStudentPagination(int page, int size) {
		
		Pageable pageable=PageRequest.of(page, size);
		Page<Student> pageStudent=studentRepository.findAll(pageable);
		
		return pageStudent.getContent();
	}

//	public List<StudentDTO> getStudentSearch(Long id, String name, String email) {
//		List<Student> studentData = studentRepository.searchSchooldetails(id,name,email);
//		List<StudentDTO> StudentDTOs = new ArrayList<>();
//		 for(Student student :studentData) {
//			 StudentDTO studentDTO = new StudentDTO();
//			 studentDTO.setId(student.getId());
//			 studentDTO.setName(student.getName());
//			 studentDTO.setEmail(student.getEmail());
//			 StudentDTOs.add(studentDTO);
//	        }
//			return StudentDTOs;
//	}

	
	public List<StudentDTO> searchStudents(String name, String email, int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Student> studentsPage = studentRepository.searchStudents( name, email, pageable);
        List<StudentDTO> StudentDTOs = new ArrayList<>();
        for(Student student:studentsPage ) {
        	StudentDTO dto = new StudentDTO();
            dto.setId(student.getId());
            dto.setName(student.getName());
            dto.setEmail(student.getEmail());
            StudentDTOs.add(dto);
        }
        return  StudentDTOs;
    }
}
