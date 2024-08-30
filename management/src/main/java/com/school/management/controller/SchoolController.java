package com.school.management.controller;

import com.school.management.dto.PaginationDto;
import com.school.management.dto.SchoolDto;
import com.school.management.entity.School;
import com.school.management.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/super-admin")
public class SchoolController {


    @Autowired
    SchoolService schoolService;

    @PostMapping("/school")
    public School createSchool(@RequestBody School school) {
        return this.schoolService.createSchool(school);
    }

    @GetMapping("/school")
    public List<School> retrieveSchools() {
        return this.schoolService.retrieveSchool();
    }

    @GetMapping("/school/{id}")
    public School retrieveSchoolId(@PathVariable Long id) {
        return this.schoolService.retrieveSchoolId(id);
    }

    @GetMapping("/school/pagination")
    public List<SchoolDto> getschoolPagination(PaginationDto paginationDto) {
        return schoolService.getschoolPagination(paginationDto.getPage(), paginationDto.getSize());
    }

    @GetMapping("/school/search")
    public List<SchoolDto> getschoolSearch(String name, String address) {
        return schoolService.getschoolSearch(name, address);
    }

    @PutMapping("/school/{id}")
    public School update(@PathVariable("id") Long id, @RequestBody School school) throws AccountNotFoundException {
        return this.schoolService.update(id, school);
    }

    @DeleteMapping("school/{id}")
    public String deleteSchool(@PathVariable Long id) {
        schoolService.deleteSchool(id);
        return "Message: Successfully delete...";
    }

}
