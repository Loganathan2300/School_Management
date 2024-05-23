package com.school.management.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.Entity.School;
import com.school.management.Service.SchoolService;

@RestController
@RequestMapping("/api/v1")
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
	
	@PutMapping("/school/{id}")
    public School update(@PathVariable("id") Long id, @RequestBody School school) throws AccountNotFoundException {
        return this.schoolService.update(id, school);
    }
	
	@DeleteMapping("/school/{id}")
    public Map<String, String> removeById(@PathVariable("id") Long id) {
        Map<String, String> response=new HashMap<>();
        response.put("message",this.schoolService.removeSchool(id));
        return response;
    }
	
}
