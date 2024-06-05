package com.school.management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.school.management.DTO.SchoolDto;
import com.school.management.entity.School;
import com.school.management.repository.SchoolRepository;

@Service
public class SchoolService {

	@Autowired
	SchoolRepository schoolRepository;
	
	public School createSchool(School school) {
        return schoolRepository.save(school);
    }
	
	public List<School> retrieveSchool() {
        return schoolRepository.findAll();
    }
	
	public School retrieveSchoolId(Long id) {
		return schoolRepository.findById(id).orElse(null);
	}
	
	 public School update(long id, School school) throws AccountNotFoundException { 
		 Optional <School> CollegeData=schoolRepository.findById(id);
		 if(CollegeData.isEmpty()) {
			 throw new AccountNotFoundException("User Not Found");
		 }
		 School finalCollege=CollegeData.orElseThrow();
		 if(school.getName()!=null) {
			 finalCollege.setName(school.getName());
		 }
		 if(school.getAddress()!=null) {
			 finalCollege.setAddress(school.getAddress());
		 }
		return this.schoolRepository.save(finalCollege);
	    }
	
//	public String removeSchool(Long id) {
//		Optional<School> optionalSchool = schoolRepository.findById(id);
//		if(optionalSchool.isPresent()) {
//			schoolRepository.deleteById(id);
//			return "School Deleted..";
//		}else {
//			return "school_details not found...";
//		}
//	}
	
	 public void deleteSchool(Long id) {
		 schoolRepository.deleteById(id);
	    }
	 
	 public List<SchoolDto> getschoolPagination(int page, int size) {
			Pageable pageable = PageRequest.of(page, size);
	        Page<School> pagedSchool = schoolRepository.findAll(pageable);
	        List<School> SchoolList = pagedSchool.getContent();
	        List<SchoolDto> SchoolDTOs = new ArrayList<>();
	        
	        for(School school :SchoolList) {
	        	SchoolDto schoolDTO = new SchoolDto();
	        	schoolDTO.setName(school.getName());
	        	schoolDTO.setAddress(school.getAddress());
	        	SchoolDTOs.add(schoolDTO);
	        }
			return SchoolDTOs;
		}

	public List<SchoolDto> getschoolSearch(String name,String address) {
		 List<School> schoolData = schoolRepository.searchSchooldetails(name,address);
		 List<SchoolDto> SchoolDTOs = new ArrayList<>();
		 for(School school :schoolData) {
	        	SchoolDto schoolDTO = new SchoolDto();
	        	schoolDTO.setName(school.getName());
	        	schoolDTO.setAddress(school.getAddress());
	        	SchoolDTOs.add(schoolDTO);
	        }
			return SchoolDTOs;
	}
	
}
