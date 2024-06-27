package com.school.management.controller;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.dto.PaginationDto;
import com.school.management.dto.SchoolDto;
import com.school.management.entity.School;
import com.school.management.service.SchoolService;

@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin(origins = "http://localhost:3000/")
public class SchoolController {
	

	@Autowired
	SchoolService schoolService;
	
	@PostMapping("/school")
	public School createSchool(@RequestBody School school) {
		return this.schoolService.createSchool(school);
	}	
	
	@GetMapping("/school")
	public List<School> retrieveSchools(){
		return this.schoolService.retrieveSchool();
	}
	
	@GetMapping("/school/{id}")
	public School retrieveSchoolId(@PathVariable("id") Long id) {
		return this.schoolService.retrieveSchoolId(id);
	}
	
	@GetMapping("/school/pagination")
	public List<SchoolDto> getschoolPagination(PaginationDto paginationDto) {
		return schoolService.getschoolPagination(paginationDto.getPage(),paginationDto.getSize());
	}
	
	@GetMapping("/school/search")
	public List<SchoolDto> getschoolSearch( String name,String address) {
		return schoolService.getschoolSearch(name,address);
	}
	
	@PutMapping("/school/{id}")
    public School update(@PathVariable("id") Long id, @RequestBody School school) throws AccountNotFoundException {
        return this.schoolService.update(id, school);
    }
	
	@DeleteMapping("school/{id}")
    public void deleteSchool(@PathVariable Long id) {
		schoolService.deleteSchool(id);
    }
	
}
