package com.school.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.dto.PaginationDto;
import com.school.management.dto.StudentDTO;
import com.school.management.entity.School;
import com.school.management.entity.Student;
import com.school.management.service.StudentService;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	 @PostMapping("/schools/students/{Id}")
	 public School addStudentToSchool(@PathVariable Long Id,@RequestBody Student student) {
	       return this.studentService.addStudentToSchool(Id, student);
	  }
	
	@PostMapping("/student") 
	public Student createStudent(@RequestBody final Student student) {
		return this.studentService.createStudent(student);
	}
	
	@GetMapping("/student")
	public List<Student> retriveStudent(){
		return this.studentService.retriveStudent();
	}
	
	@GetMapping("/student/{id}")
	public Student retrieveStudentId(@PathVariable Long id) {
		return this.studentService.retrieveStudentId(id);
	}
	
	@GetMapping("/student/pagination")
	public List<Student>getStudentPagination(PaginationDto paginationDto){
		return this.studentService.getStudentPagination(paginationDto.getPage(),paginationDto.getSize());
	}
	
	@GetMapping("/student/search")
    public List<StudentDTO> searchStudents( String name, String email,int page,int size,String sortField,String sortDirection) {
        return studentService.searchStudents(name, email, page, size, sortField, sortDirection);
    }	
	
	@DeleteMapping("student/{id}")
    public void deleteSchool(@PathVariable Long id) {
		studentService.deleteSchool(id);
    }

}
