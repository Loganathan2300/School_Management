package com.school.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.dto.AnswerDTO;
import com.school.management.dto.PaginationDto;
import com.school.management.dto.StudentScore;
import com.school.management.entity.Answer;
import com.school.management.service.AnswerService;

@RestController
@RequestMapping("api/v1")
public class AnswerController {

	@Autowired
	AnswerService answerService;
	
	@PostMapping("/answer")
	@PreAuthorize("hasAnyAuthority('User')")
    public Answer createAnswer(@RequestBody Answer answer) {
        return answerService.createAnswer(answer);
    }
    
    @GetMapping("/answer/{studentId}")
    @PreAuthorize("hasAnyAuthority('Admin') or hasAnyAuthority('User') or hasAnyAuthority('Super_Admin')")
    public String getStudentScore(@PathVariable Long studentId) {
        return answerService.calculateScore(studentId);
    }
    
    @GetMapping("/answer")
	public List<AnswerDTO> getStudents(@RequestParam(required = false) String search,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false, defaultValue = "name") String sortField,
            @RequestParam(required = false, defaultValue = "asc") String sortDirection) {
       return answerService.getStudents(search, page, size, sortField, sortDirection);
    }
    
    @GetMapping("/answers")
    @PreAuthorize("hasAnyAuthority('Admin') or hasAnyAuthority('Super_Admin')")
    public List<AnswerDTO> getAllAnswers() {
        return answerService.getAllAnswers();
    }
    
    @GetMapping("/answer/students-score")
    @PreAuthorize("hasAnyAuthority('Admin') or hasAnyAuthority('Super_Admin')")
    public List<StudentScore> getAllStudentScores() {
        return answerService.getAllStudentScores();
    }
    
    @GetMapping("/answer/pagination")
    @PreAuthorize("hasAnyAuthority('Admin') or hasAnyAuthority('User') or hasAnyAuthority('Super_Admin')")
    public List<AnswerDTO> getPaginatedAnswers( PaginationDto paginationDto) {
        return answerService.getAnswers(paginationDto.getPage(),paginationDto.getSize());
    }
    
    @GetMapping("/answer/search")
    @PreAuthorize("hasAnyAuthority('Admin') or hasAnyAuthority('User') or hasAnyAuthority('Super_Admin')")
    public List<AnswerDTO> searchAnswers(Boolean answers,Long student,Long question,Long choice) {
        return answerService.searchAnswers(answers, student, question, choice);
    }
    
    @PutMapping("answer/{id}")
    @PreAuthorize("hasAnyAuthority('Admin') or hasAnyAuthority('User') or hasAnyAuthority('Super_Admin')")
    public Answer updateAnswer(@PathVariable Long id, @RequestBody Answer answer) {
        answer.setId(id); 
        return answerService.updateAnswer(answer);
    }

    @DeleteMapping("answer/{id}")
    @PreAuthorize("hasAnyAuthority('Admin') or hasAnyAuthority('Super_Admin')")
    public void deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
    }
    
}
