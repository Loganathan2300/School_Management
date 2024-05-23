package com.school.management.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.Entity.School;
import com.school.management.Entity.Teacher;
import com.school.management.Service.TeacherService;

@RestController
@RequestMapping("/api/v1")
public class TeacherController {

	@Autowired
	TeacherService teacherService;
	
	@PostMapping("/teacher")
	public Teacher createTeacher(@RequestBody final Teacher teacher) {
		return this.teacherService.createTeacher(teacher);
	}
	
	@PostMapping("/teacher/school/{id}")
	public School addtoTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
		return this.teacherService.addteacherToSchool(id,teacher);
	}
	
	@GetMapping("/teacher")
	public List<Teacher> retriveTeachers(){
		return this.teacherService.retriveTeachers();
	}
	
	@GetMapping("/teacher/{id}")
	public Teacher retriveTeacher(@PathVariable Long id ) {
		return this.teacherService.retriveTeacher(id);
	}
	
	@DeleteMapping("/teacher/{id}")
	public Map<String, String> removeId(@PathVariable Long id){
		Map<String, String> response = new HashMap<>();
		response.put("Message", teacherService.removeData(id));
		return response;
	}
}
