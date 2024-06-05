package com.school.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.DTO.PaginationDto;
import com.school.management.DTO.StudentDTO;
import com.school.management.entity.School;
import com.school.management.entity.Student;
import com.school.management.service.StudentService;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	 @PostMapping("/schools/students/{Id}")
	 public School addStudentToSchool(@PathVariable Long Id, @Validated @RequestBody Student student) {
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
	public List<StudentDTO>getStudentSearch(Long id,String name,String email){
		return this.studentService.getStudentSearch(id,name,email);
	}
	
//	@DeleteMapping("/student/{id}")
//	public Map<String, String> removeId(@PathVariable Long id){
//		Map<String, String> response = new HashMap<>();
//		response.put("Message", studentService.removeId(id));
//		return response;
//	}	
	
	@DeleteMapping("student/{id}")
    public void deleteSchool(@PathVariable Long id) {
		studentService.deleteSchool(id);
    }

}
