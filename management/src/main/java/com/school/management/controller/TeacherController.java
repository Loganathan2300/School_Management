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
import com.school.management.entity.School;
import com.school.management.entity.Teacher;
import com.school.management.service.TeacherService;

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
	
	@GetMapping("/teacher/pagination")
    public List<Teacher> getPaginatedTeacher( PaginationDto paginationDto) {
        return teacherService.getPaginatedTeacher(paginationDto.getPage(),paginationDto.getSize());
    }
		
	@GetMapping("/teacher/search")
    public List<Teacher> getSearchTeacher(String name,String subject,int page,int size,String sortField,String sortDirection) {
        return teacherService.getSearchTeacher(name,subject,page,size,sortField,sortDirection);
    }
	
	@DeleteMapping("teacher/{id}")
    public void deleteTeacher(@PathVariable Long id) {
		teacherService.deleteTeacherId(id);
    }
}
