package com.school.management.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.Entity.School;
import com.school.management.Entity.Student;
import com.school.management.Service.StudentService;

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
	
	@DeleteMapping("/student/{id}")
	public Map<String, String> removeId(@PathVariable Long id){
		Map<String, String> response = new HashMap<>();
		response.put("Message", studentService.removeId(id));
		return response;
	}
	
////	@DeleteMapping("/schools/{schoolId}/students/{studentId}")
//	@DeleteMapping("/students/{schoolId}/{studentId}")
//    public  Map<String, String> removeStudentFromSchool(@PathVariable Long schoolId, @PathVariable Long studentId) {
//		Map<String, String> response = new HashMap<>();
//		response.put("Message", studentService.removeStudentFromSchool(schoolId, studentId));
//		return response;
//    }	

}
