package com.school.management.dto;

import com.school.management.entity.School;


public class StudentDTO {

	    private Long id;
	    private String name;
	    private String email;
	    private String schoolname;
	    private School school_name;
	    
	    
		public String getSchoolName() {
			return schoolname;
		}
		public void setSchoolName(String school) {
			this.schoolname = school;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public School getSchool_name() {
			return school_name;
		}
		public void setSchool_name(School school_name) {
			this.school_name = school_name;
		}
		
	    
	    
}
