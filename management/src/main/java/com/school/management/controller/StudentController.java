package com.school.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.dto.StudentDTO;
//import com.school.management.dto.StudentSearchCriteria;
//import com.school.management.dto.PaginationDto;
//import com.school.management.dto.StudentDTO;
import com.school.management.entity.School;
import com.school.management.entity.Student;
import com.school.management.service.StudentService;

@RestController
@RequestMapping("/api/v1/user")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	 @PostMapping("/students/{schoolId}")
	 public School addStudentToSchool(@PathVariable Long schoolId,@RequestBody Student student) {
	       return this.studentService.addStudentToSchool(schoolId, student);
	  }
	
	@PostMapping("/student") 
	public Student createStudent(@RequestBody final Student student) {
		return this.studentService.createStudent(student);
	}
	
	@GetMapping("/student/{id}")
	public Student retrieveStudentId(@PathVariable Long id) {
		return this.studentService.retrieveStudentId(id);
	}
	
	@GetMapping("/student")
	public List<StudentDTO> getStudents(@RequestParam(required = false) String search,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false, defaultValue = "name") String sortField,
            @RequestParam(required = false, defaultValue = "asc") String sortDirection) {
       return studentService.getStudents(search, page, size, sortField, sortDirection);
    }
	
	@DeleteMapping("student/{id}")
    public void deleteSchool(@PathVariable Long id) {
		studentService.deleteSchool(id);
    }
	
	
	@GetMapping("/students")
	public List<Student> retriveStudent(){
		return this.studentService.retriveStudent();
	}
	
//	@GetMapping("/student/pagination")
//	public List<Student>getStudentPagination(PaginationDto paginationDto){
//		return this.studentService.getStudentPagination(paginationDto.getPage(),paginationDto.getSize());
//	}
//	
//	@GetMapping("/student/search")
//    public List<StudentDTO> searchStudents( String name,String schoolname, String email,int page,int size,String sortField,String sortDirection) {
//        return studentService.searchStudents(name,schoolname, email, page, size, sortField, sortDirection);
//    }

}
